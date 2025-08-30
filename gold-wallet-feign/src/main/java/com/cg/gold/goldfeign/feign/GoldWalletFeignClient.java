package com.cg.gold.goldfeign.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cg.gold.goldfeign.entity.User;

@FeignClient("user-service")
public interface GoldWalletFeignClient {

	@GetMapping("/api/v2/users")
	List<User> getAllUsers();
}
