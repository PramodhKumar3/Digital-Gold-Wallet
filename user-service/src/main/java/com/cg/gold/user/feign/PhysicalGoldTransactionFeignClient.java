package com.cg.gold.user.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.gold.user.dto.PhysicalGoldTransactionDTO;

@FeignClient(name = "physical-gold-service", url = "http://localhost:8107")
public interface PhysicalGoldTransactionFeignClient {

	@GetMapping("/api/v2/physical_gold_transactions")
	public List<PhysicalGoldTransactionDTO> getAllTransactions();

	@GetMapping("/api/v2/physical_gold_transactions/{transaction_id}")
	public PhysicalGoldTransactionDTO getById(@PathVariable("transaction_id") Integer transactionId);

	@GetMapping("/api/v2/physical_gold_transactions/by_user/{user_id}")
	public List<PhysicalGoldTransactionDTO> getByUserId(@PathVariable("user_id") Integer userId);
}
