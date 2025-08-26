package com.cg.gold.user.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cg.gold.user.dto.TransactionHistoryDTO;

@FeignClient(name = "transaction-history-service", url = "http://localhost:8105")
public interface TransactionHistoryFeignClient {

	@GetMapping("/api/v2/transaction_history")
	public List<TransactionHistoryDTO> getAllTransactionHistory();
}
