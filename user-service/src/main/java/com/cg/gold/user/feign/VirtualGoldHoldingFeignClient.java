package com.cg.gold.user.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.gold.user.dto.VirtualGoldHoldingDTO;

@FeignClient(name = "virtual-gold-service", url = "http://localhost:8108")
public interface VirtualGoldHoldingFeignClient {

	@GetMapping("/api/v2/virtual_gold_holding")
	public List<VirtualGoldHoldingDTO> getAllHoldings();

	@GetMapping("/api/v2/virtual_gold_holding/{holding_id}")
	public VirtualGoldHoldingDTO getHoldingById(@PathVariable("holding_id") Integer holdingId);

	@GetMapping("/api/v2/virtual_gold_holding/users/{user_id}")
	public List<VirtualGoldHoldingDTO> getHoldingsByUser(@PathVariable("user_id") Integer userId);
}
