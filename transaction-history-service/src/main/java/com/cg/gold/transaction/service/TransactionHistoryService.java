package com.cg.gold.transaction.service;

import java.util.List;

import com.cg.gold.transaction.entity.TransactionHistory;
import com.cg.gold.transaction.entity.TransactionHistory.TransactionType;
import com.cg.gold.transaction.exception.TransactionHistoryException;

public interface TransactionHistoryService {

	public List<TransactionHistory> getAllTransactionHistory();

	public TransactionHistory getTransactionHistoryById(Integer transactionId) throws TransactionHistoryException;

	public List<TransactionHistory> getAllTransactionHistoryByUserId(Integer userId) throws TransactionHistoryException;

	public List<TransactionHistory> getAllSuccessTransactionHistory();

	public List<TransactionHistory> getAllFailedTransactionHistory();

	public List<TransactionHistory> getAllTransactionHistoryByTransactionType(TransactionType transactionType)
			throws TransactionHistoryException;

	public void addTransactionHistory(TransactionHistory newTransactionHistory) throws TransactionHistoryException;

	public List<TransactionHistory> getTransactionsByUserSorted(Integer userId) throws TransactionHistoryException;
}
