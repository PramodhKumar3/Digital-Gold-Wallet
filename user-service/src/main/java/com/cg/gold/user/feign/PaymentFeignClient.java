package com.cg.gold.user.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cg.gold.user.dto.PaymentDTO;

@FeignClient(name = "payment-service", url = "http://localhost:8106")
public interface PaymentFeignClient {

	@GetMapping("/api/v2/payments")
	public List<PaymentDTO> getAllPayments();
}
