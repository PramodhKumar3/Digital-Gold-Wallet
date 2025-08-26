package com.cg.gold.virtual.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.gold.virtual.entity.PhysicalGoldTransaction;

import jakarta.validation.Valid;

@FeignClient(name = "physical-gold-service", url = "http://localhost:8107")
public interface PhysicalGoldTransactionFeignClient {

	@PostMapping("/api/v2/physical_gold_transactions/add")
	public String addTransaction(@Valid @RequestBody PhysicalGoldTransaction transaction);
}
