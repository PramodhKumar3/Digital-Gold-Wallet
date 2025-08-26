package com.cg.gold.virtual.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.gold.virtual.dto.VendorDTO;
import com.cg.gold.virtual.entity.PhysicalGoldTransaction;
import com.cg.gold.virtual.entity.User;
import com.cg.gold.virtual.entity.VendorBranch;
import com.cg.gold.virtual.entity.VirtualGoldHolding;
import com.cg.gold.virtual.exception.VirtualGoldHoldingException;
import com.cg.gold.virtual.feign.PhysicalGoldTransactionFeignClient;
import com.cg.gold.virtual.feign.UserFeignClient;
import com.cg.gold.virtual.feign.VendorBranchFeignClient;
import com.cg.gold.virtual.feign.VendorFeignClient;
import com.cg.gold.virtual.repository.VirtualGoldHoldingRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VirtualGoldHoldingServiceImpl implements VirtualGoldHoldingService {

	@Autowired
	private VirtualGoldHoldingRepository virtualGoldHoldingRepository;

	@Autowired
	private PhysicalGoldTransactionFeignClient physicalGoldTransactionFeignClient;

	@Autowired
	private UserFeignClient userFeignClient;

	@Autowired
	private VendorBranchFeignClient vendorBranchFeignClient;

	@Autowired
	private VendorFeignClient vendorFeignClient;

	@Override
	public List<VirtualGoldHolding> getAllVirtualGoldHoldings() {
		return virtualGoldHoldingRepository.findAll();
	}

	@Override
	public List<VirtualGoldHolding> getAllVirtualGoldHoldingByUserId(Integer userId)
			throws VirtualGoldHoldingException {
		User user = userFeignClient.getUserById(userId);
		if (user == null || user.getUserId() == null)
			throw new VirtualGoldHoldingException("UserService.USER_NOT_FOUND");
		List<VirtualGoldHolding> holdings = virtualGoldHoldingRepository.findByUserUserId(userId);
		if (holdings.isEmpty())
			throw new VirtualGoldHoldingException("VirtualGoldService.USER_NOT_FOUND");
		return holdings;
	}

	@Override
	public VirtualGoldHolding getVirtualGoldHoldingById(Integer holdingId) throws VirtualGoldHoldingException {
		return virtualGoldHoldingRepository.findById(holdingId)
				.orElseThrow(() -> new VirtualGoldHoldingException("VirtualGoldService.HOLDING_NOT_FOUND"));
	}

	@Override
	public List<VirtualGoldHolding> getVirtualGoldHoldingByUserAndVendor(Integer userId, Integer vendorId)
			throws VirtualGoldHoldingException {
		User user = userFeignClient.getUserById(userId);
		if (user == null || user.getUserId() == null)
			throw new VirtualGoldHoldingException("UserService.USER_NOT_FOUND");
		VendorDTO vendorDTO = vendorFeignClient.getVendorById(vendorId);
		if (vendorDTO == null || vendorDTO.getVendorId() == null)
			throw new VirtualGoldHoldingException("VendorService.VENDOR_NOT_FOUND");
		List<VirtualGoldHolding> holdings = virtualGoldHoldingRepository.findByUserUserIdAndBranchVendorVendorId(userId,
				vendorId);
		if (holdings.isEmpty())
			throw new VirtualGoldHoldingException("VirtualGoldService.USER_AND_VENDOR_NOT_FOUND");
		return holdings;
	}

	@Override
	public void addVirtualGoldHolding(VirtualGoldHolding newVirtualGoldHolding) throws VirtualGoldHoldingException {
		Integer userId = newVirtualGoldHolding.getUser().getUserId();
		User user = userFeignClient.getUserById(userId);
		if (user == null || user.getUserId() == null)
			throw new VirtualGoldHoldingException("UserService.USER_NOT_FOUND");
		Integer branchId = newVirtualGoldHolding.getBranch().getBranchId();
		VendorBranch branch = vendorBranchFeignClient.getBranchById(branchId);
		if (branch == null || branch.getBranchId() == null)
			throw new VirtualGoldHoldingException("VendorBranchService.BRANCH_NOT_FOUND");
		newVirtualGoldHolding.setUser(user);
		newVirtualGoldHolding.setBranch(branch);
		newVirtualGoldHolding.setCreatedAt(LocalDateTime.now());
		virtualGoldHoldingRepository.save(newVirtualGoldHolding);
	}

	@Override
	public void convertVirtualToPhysical(Integer holdingId) throws VirtualGoldHoldingException {
		VirtualGoldHolding holding = virtualGoldHoldingRepository.findById(holdingId)
				.orElseThrow(() -> new VirtualGoldHoldingException("VirtualGoldService.HOLDING_NOT_FOUND"));
		physicalGoldTransactionFeignClient.addTransaction(new PhysicalGoldTransaction(holding.getUser(),
				holding.getBranch(), holding.getQuantity(), holding.getUser().getAddress(), LocalDateTime.now()));
		virtualGoldHoldingRepository.delete(holding);
	}

	@Override
	public Integer updateVirtualGoldHolding(Integer holdingId, VirtualGoldHolding updatedHolding)
			throws VirtualGoldHoldingException {
		VirtualGoldHolding existing = virtualGoldHoldingRepository.findById(holdingId)
				.orElseThrow(() -> new VirtualGoldHoldingException("VirtualGoldService.HOLDING_NOT_FOUND"));
		Integer userId = updatedHolding.getUser().getUserId();
		User user = userFeignClient.getUserById(userId);
		if (user == null || user.getUserId() == null)
			throw new VirtualGoldHoldingException("UserService.USER_NOT_FOUND");
		Integer branchId = updatedHolding.getBranch().getBranchId();
		VendorBranch branch = vendorBranchFeignClient.getBranchById(branchId);
		if (branch == null || branch.getBranchId() == null)
			throw new VirtualGoldHoldingException("VendorBranchService.BRANCH_NOT_FOUND");
		existing.setUser(user);
		existing.setBranch(branch);
		existing.setQuantity(updatedHolding.getQuantity());
		virtualGoldHoldingRepository.save(existing);
		return existing.getHoldingId();
	}

	@Override
	public Double getTotalVirtualGoldByUser(Integer userId) throws VirtualGoldHoldingException {
		User user = userFeignClient.getUserById(userId);
		if (user == null || user.getUserId() == null)
			throw new VirtualGoldHoldingException("UserService.USER_NOT_FOUND");
		return virtualGoldHoldingRepository.getTotalQuantityByUserId(userId);
	}

}
