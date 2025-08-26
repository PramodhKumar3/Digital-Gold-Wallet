package com.cg.gold.vendorbranch.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cg.gold.vendorbranch.dto.TransactionHistoryDTO;

@FeignClient(name = "transaction-history-service", url = "http://localhost:8105")
public interface TransactionHistoryFeignClient {

	@GetMapping("/api/v2/transaction_history")
	public List<TransactionHistoryDTO> getAllTransactionHistory();
}
