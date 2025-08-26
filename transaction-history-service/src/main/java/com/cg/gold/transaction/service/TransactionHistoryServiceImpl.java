package com.cg.gold.transaction.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.gold.transaction.entity.TransactionHistory;
import com.cg.gold.transaction.entity.TransactionHistory.TransactionStatus;
import com.cg.gold.transaction.entity.TransactionHistory.TransactionType;
import com.cg.gold.transaction.entity.User;
import com.cg.gold.transaction.entity.VendorBranch;
import com.cg.gold.transaction.exception.TransactionHistoryException;
import com.cg.gold.transaction.feign.UserFeignClient;
import com.cg.gold.transaction.feign.VendorBranchFeignClient;
import com.cg.gold.transaction.repository.TransactionHistoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	@Autowired
	private TransactionHistoryRepository transactionHistoryRepository;

	@Autowired
	private UserFeignClient userFeignClient;

	@Autowired
	private VendorBranchFeignClient vendorBranchFeignClient;

	@Override
	public List<TransactionHistory> getAllTransactionHistory() {
		return transactionHistoryRepository.findAll();
	}

	@Override
	public TransactionHistory getTransactionHistoryById(Integer transactionId) throws TransactionHistoryException {
		return transactionHistoryRepository.findById(transactionId)
				.orElseThrow(() -> new TransactionHistoryException("TransactionHistroryService.TRANSACTION_NOT_FOUND"));
	}

	@Override
	public List<TransactionHistory> getAllTransactionHistoryByUserId(Integer userId)
			throws TransactionHistoryException {
		User user = userFeignClient.getUserById(userId);
		if (user == null || user.getUserId() == null)
			throw new TransactionHistoryException("UserService.USER_NOT_FOUND");
		List<TransactionHistory> historyList = transactionHistoryRepository.findByUserUserId(userId);
		if (historyList.isEmpty()) {
			throw new TransactionHistoryException("TransactionHistroryService.NO_TRANSCTION_FOR_USER");
		}
		return historyList;
	}

	@Override
	public List<TransactionHistory> getAllSuccessTransactionHistory() {
		return transactionHistoryRepository.findByTransactionStatus(TransactionStatus.Success);
	}

	@Override
	public List<TransactionHistory> getAllFailedTransactionHistory() {
		return transactionHistoryRepository.findByTransactionStatus(TransactionStatus.Failed);
	}

	@Override
	public List<TransactionHistory> getAllTransactionHistoryByTransactionType(TransactionType transactionType)
			throws TransactionHistoryException {
		List<TransactionType> validTypes = Arrays.asList(TransactionType.Buy, TransactionType.Sell,
				TransactionType.ConvertToPhysical);
		if (!validTypes.contains(transactionType))
			throw new TransactionHistoryException("TransactionHistroryService.INVALID_TYPE");
		List<TransactionHistory> transactions = transactionHistoryRepository.findByTransactionType(transactionType);
		if (transactions.isEmpty())
			throw new TransactionHistoryException("TransactionHistroryService.NO_TRANSACTIONS_AVAILABLE");
		return transactions;
	}

	@Override
	public void addTransactionHistory(TransactionHistory newTransactionHistory) throws TransactionHistoryException {
		Integer branchId = newTransactionHistory.getBranch().getBranchId();
		VendorBranch vendorBranch = vendorBranchFeignClient.getBranchById(branchId);
		if (vendorBranch == null || vendorBranch.getBranchId() == null)
			throw new TransactionHistoryException("VendorBranchService.BRANCH_NOT_FOUND");
		Integer userId = newTransactionHistory.getUser().getUserId();
		User user = userFeignClient.getUserById(userId);
		if (user == null || user.getUserId() == null)
			throw new TransactionHistoryException("UserService.USER_NOT_FOUND");
		newTransactionHistory.setCreatedAt(LocalDateTime.now());
		transactionHistoryRepository.save(newTransactionHistory);
	}

	@Override
	public List<TransactionHistory> getTransactionsByUserSorted(Integer userId) {
		return transactionHistoryRepository.findByUserUserIdOrderByCreatedAtDesc(userId);
	}

}
