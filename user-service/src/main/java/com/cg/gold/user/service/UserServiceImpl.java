package com.cg.gold.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.gold.user.dto.PaymentDTO;
import com.cg.gold.user.dto.PhysicalGoldTransactionDTO;
import com.cg.gold.user.dto.TransactionHistoryDTO;
import com.cg.gold.user.dto.VirtualGoldHoldingDTO;
import com.cg.gold.user.entity.Address;
import com.cg.gold.user.entity.User;
import com.cg.gold.user.exception.UserException;
import com.cg.gold.user.feign.AddressFeignClient;
import com.cg.gold.user.feign.PaymentFeignClient;
import com.cg.gold.user.feign.PhysicalGoldTransactionFeignClient;
import com.cg.gold.user.feign.TransactionHistoryFeignClient;
import com.cg.gold.user.feign.VirtualGoldHoldingFeignClient;
import com.cg.gold.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressFeignClient addressFeignClient;

	@Autowired
	private TransactionHistoryFeignClient transactionHistoryFeignClient;

	@Autowired
	private PaymentFeignClient paymentFeignClient;

	@Autowired
	private VirtualGoldHoldingFeignClient virtualGoldHoldingFeignClient;

	@Autowired
	private PhysicalGoldTransactionFeignClient physicalGoldTransactionFeignClient;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Integer userId) throws UserException {
		return userRepository.findById(userId).orElseThrow(() -> new UserException("UserService.USER_NOT_FOUND"));
	}

	@Override
	public User getUserByUserName(String name) throws UserException {
		return userRepository.findByName(name).orElseThrow(() -> new UserException("UserService.NAME_NOT_FOUND"));
	}

	@Override
	public List<User> getUsersByCity(String city) throws UserException {
		List<User> users = userRepository.findByAddressCity(city);
		if (users.isEmpty())
			throw new UserException("UserService.CITY_NOT_FOUND");
		return users;
	}

	@Override
	public List<User> getUsersByState(String state) throws UserException {
		List<User> users = userRepository.findByAddressState(state);
		if (users.isEmpty())
			throw new UserException("UserService.STATE_NOT_FOUND");
		return users;
	}

	@Override
	public Double getUserBalanceById(Integer userId) throws UserException {
		userRepository.findById(userId).orElseThrow(() -> new UserException("UserService.USER_NOT_FOUND"));
		return getUserById(userId).getBalance();
	}

	@Override
	public Double getTotalVirtualGoldHoldingsByUserId(Integer userId) throws UserException {
		userRepository.findById(userId).orElseThrow(() -> new UserException("UserService.USER_NOT_FOUND"));
		List<VirtualGoldHoldingDTO> holdings = virtualGoldHoldingFeignClient.getHoldingsByUser(userId);
		if (holdings.isEmpty())
			return 0.0;
		return holdings.stream().mapToDouble(VirtualGoldHoldingDTO::getQuantity).sum();
	}

	@Override
	public Double getTotalPhysicalGoldHoldingsByUserId(Integer userId) throws UserException {
		userRepository.findById(userId).orElseThrow(() -> new UserException("UserService.USER_NOT_FOUND"));
		List<PhysicalGoldTransactionDTO> transactions = physicalGoldTransactionFeignClient.getByUserId(userId);
		if (transactions.isEmpty())
			return 0.0;
		return transactions.stream().mapToDouble(PhysicalGoldTransactionDTO::getQuantity).sum();
	}

	@Override
	public List<TransactionHistoryDTO> getUserTransactionHistory(Integer userId) throws UserException {
		userRepository.findById(userId).orElseThrow(() -> new UserException("UserService.USER_NOT_FOUND"));
		return transactionHistoryFeignClient.getAllTransactionHistory().stream()
				.filter(transaction -> transaction.getUser().getUserId() == userId).collect(Collectors.toList());
	}

	@Override
	public List<PaymentDTO> getUserPayments(Integer userId) throws UserException {
		userRepository.findById(userId).orElseThrow(() -> new UserException("UserService.USER_NOT_FOUND"));
		return paymentFeignClient.getAllPayments().stream().filter(payment -> payment.getUser().getUserId() == userId)
				.collect(Collectors.toList());
	}

	@Override
	public void createUser(User newUser) throws UserException {
		Integer addressId = newUser.getAddress().getAddressId();
		Address address = addressFeignClient.getAddressById(addressId);
		if (address == null || address.getAddressId() == null)
			throw new UserException("AddressService.ADDRESS_NOT_FOUND");
		newUser.setAddress(address);
		if (newUser.getBalance() < 100)
			throw new UserException("UserService.MINIMUM_BALANCE");
		newUser.setCreatedAt(LocalDateTime.now());
		userRepository.save(newUser);
	}

	@Override
	public void updateUser(Integer userId, User userDetails) throws UserException {
		User existing = userRepository.findById(userId)
				.orElseThrow(() -> new UserException("UserService.USER_NOT_FOUND"));
		existing.setName(userDetails.getName());
		existing.setEmail(userDetails.getEmail());
		existing.setBalance(userDetails.getBalance());
		Address address = userDetails.getAddress();
		existing.setAddress(address);
		userRepository.save(existing);
	}

	@Override
	public void updateUserBalance(Integer userId, Double amount) throws UserException {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserException("UserService.USER_NOT_FOUND"));
		Integer addressId = user.getAddress().getAddressId();
		Address address = addressFeignClient.getAddressById(addressId);
		if (address == null || address.getAddressId() == null)
			throw new UserException("AddressService.ADDRESS_NOT_FOUND");
		user.setAddress(address);
		user.setBalance(amount);
		userRepository.save(user);
	}

	@Override
	public void updateUserAddress(Integer userId, Integer addressId) throws UserException {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserException("UserService.USER_NOT_FOUND"));
		Address address = addressFeignClient.getAddressById(addressId);
		if (address == null || address.getAddressId() == null)
			throw new UserException("AddressService.ADDRESS_NOT_FOUND");
		user.setAddress(address);
		userRepository.save(user);
	}

}
