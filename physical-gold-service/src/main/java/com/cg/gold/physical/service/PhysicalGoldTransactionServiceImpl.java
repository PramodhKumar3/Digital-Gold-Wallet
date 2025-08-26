package com.cg.gold.physical.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.gold.physical.entity.Address;
import com.cg.gold.physical.entity.PhysicalGoldTransaction;
import com.cg.gold.physical.entity.User;
import com.cg.gold.physical.entity.VendorBranch;
import com.cg.gold.physical.exception.PhysicalGoldTransactionException;
import com.cg.gold.physical.feign.AddressFeignClient;
import com.cg.gold.physical.feign.UserFeignClient;
import com.cg.gold.physical.feign.VendorBranchFeignClient;
import com.cg.gold.physical.repository.PhysicalGoldTransactionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PhysicalGoldTransactionServiceImpl implements PhysicalGoldTransactionService {

	@Autowired
	private AddressFeignClient addressFeignClient;

	@Autowired
	private PhysicalGoldTransactionRepository physicalGoldTransactionRepository;

	@Autowired
	private UserFeignClient userFeignClient;

	@Autowired
	private VendorBranchFeignClient vendorBranchFeignClient;

	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactions() {
		return physicalGoldTransactionRepository.findAll();
	}

	@Override
	public PhysicalGoldTransaction getPhysicalGoldTransactionById(Integer transactionId)
			throws PhysicalGoldTransactionException {
		return physicalGoldTransactionRepository.findById(transactionId)
				.orElseThrow(() -> new PhysicalGoldTransactionException("PhysicalGoldService.TRANSACTION_NOT_FOUND"));
	}

	@Override
	public List<PhysicalGoldTransaction> getPhysicalGoldTransactionByUserId(Integer userId)
			throws PhysicalGoldTransactionException {
		User user = userFeignClient.getUserById(userId);
		if (user == null || user.getUserId() == null)
			throw new PhysicalGoldTransactionException("UserService.USER_NOT_FOUND");
		List<PhysicalGoldTransaction> transactions = physicalGoldTransactionRepository.findAllByUserUserId(userId);
		if (transactions.isEmpty()) {
			throw new PhysicalGoldTransactionException("PhysicalGoldService.TRANSACTION_USER_FOUND");
		}
		return transactions;
	}

	@Override
	public List<PhysicalGoldTransaction> getPhysicalGoldTransactionByBranchId(Integer branchId)
			throws PhysicalGoldTransactionException {
		VendorBranch vendorBranch = vendorBranchFeignClient.getBranchById(branchId);
		if (vendorBranch == null || vendorBranch.getBranchId() == null)
			throw new PhysicalGoldTransactionException("VendorBranchService.BRANCH_NOT_FOUND");
		List<PhysicalGoldTransaction> transactions = physicalGoldTransactionRepository
				.findAllByBranchBranchId(branchId);
		if (transactions.isEmpty()) {
			throw new PhysicalGoldTransactionException("PhysicalGoldService.NO_TRANSACTION_FOR_BRANCH");
		}
		return transactions;
	}

	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionByDeliveryCity(String city)
			throws PhysicalGoldTransactionException {
		List<PhysicalGoldTransaction> transactions = physicalGoldTransactionRepository.findByDeliveryAddressCity(city);
		if (transactions == null || transactions.isEmpty()) {
			throw new PhysicalGoldTransactionException("PhysicalGoldService.NO_TRANSACTION_FOR_CITY");
		}
		return transactions;
	}

	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionByDeliveryState(String state)
			throws PhysicalGoldTransactionException {
		List<PhysicalGoldTransaction> transactions = physicalGoldTransactionRepository
				.findByDeliveryAddressState(state);
		if (transactions == null || transactions.isEmpty()) {
			throw new PhysicalGoldTransactionException("PhysicalGoldService.NO_TRANSACTION_FOR_STATE");
		}
		return transactions;
	}

	@Override
	public void addPhysicalGoldTransaction(PhysicalGoldTransaction newPhysicalGoldTransaction)
			throws PhysicalGoldTransactionException {
		Integer branchId = newPhysicalGoldTransaction.getBranch().getBranchId();
		VendorBranch vendorBranch = vendorBranchFeignClient.getBranchById(branchId);
		if (vendorBranch == null || vendorBranch.getBranchId() == null)
			throw new PhysicalGoldTransactionException("VendorBranchService.BRANCH_NOT_FOUND");
		Integer addressId = newPhysicalGoldTransaction.getDeliveryAddress().getAddressId();
		Address address = addressFeignClient.getAddressById(addressId);
		if (address == null || address.getAddressId() == null)
			throw new PhysicalGoldTransactionException("AddressService.ADDRESS_NOT_FOUND");
		Integer userId = newPhysicalGoldTransaction.getUser().getUserId();
		User user = userFeignClient.getUserById(userId);
		if (user == null || user.getUserId() == null)
			throw new PhysicalGoldTransactionException("UserService.USER_NOT_FOUND");
		newPhysicalGoldTransaction.setCreatedAt(LocalDateTime.now());
		physicalGoldTransactionRepository.save(newPhysicalGoldTransaction);
	}

	@Override
	public Integer updatePhysicalGoldTransaction(Integer transactionId, PhysicalGoldTransaction updatedTransaction)
			throws PhysicalGoldTransactionException {
		PhysicalGoldTransaction existing = physicalGoldTransactionRepository.findById(transactionId)
				.orElseThrow(() -> new PhysicalGoldTransactionException("PhysicalGoldService.TRANSACTION_NOT_FOUND"));
		Integer branchId = updatedTransaction.getBranch().getBranchId();
		VendorBranch vendorBranch = vendorBranchFeignClient.getBranchById(branchId);
		if (vendorBranch == null || vendorBranch.getBranchId() == null)
			throw new PhysicalGoldTransactionException("VendorBranchService.BRANCH_NOT_FOUND");
		Integer addressId = updatedTransaction.getDeliveryAddress().getAddressId();
		Address address = addressFeignClient.getAddressById(addressId);
		if (address == null || address.getAddressId() == null)
			throw new PhysicalGoldTransactionException("AddressService.ADDRESS_NOT_FOUND");
		Integer userId = updatedTransaction.getUser().getUserId();
		User user = userFeignClient.getUserById(userId);
		if (user == null || user.getUserId() == null)
			throw new PhysicalGoldTransactionException("UserService.USER_NOT_FOUND");
		existing.setUser(user);
		existing.setBranch(vendorBranch);
		existing.setQuantity(updatedTransaction.getQuantity());
		existing.setDeliveryAddress(address);
		existing.setCreatedAt(LocalDateTime.now());

		physicalGoldTransactionRepository.save(existing);
		return existing.getTransactionId();
	}

	@Override
	public Double getTotalPhysicalGoldByUser(Integer userId) throws PhysicalGoldTransactionException {
		User user = userFeignClient.getUserById(userId);
		if (user == null || user.getUserId() == null)
			throw new PhysicalGoldTransactionException("UserService.USER_NOT_FOUND");
		return physicalGoldTransactionRepository.getTotalQuantityByUserId(userId);
	}

}
