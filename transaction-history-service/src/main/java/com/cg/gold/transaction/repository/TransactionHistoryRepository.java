package com.cg.gold.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.gold.transaction.entity.TransactionHistory;
import com.cg.gold.transaction.entity.TransactionHistory.TransactionStatus;
import com.cg.gold.transaction.entity.TransactionHistory.TransactionType;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {

	List<TransactionHistory> findByUserUserId(Integer userId);

	List<TransactionHistory> findByTransactionStatus(TransactionStatus status);

	List<TransactionHistory> findByTransactionType(TransactionType type);

	List<TransactionHistory> findByUserUserIdOrderByCreatedAtDesc(Integer userId);

}
