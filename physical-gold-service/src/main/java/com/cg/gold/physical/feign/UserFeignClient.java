package com.cg.gold.physical.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.gold.physical.entity.User;

@FeignClient(name = "user-service", url = "http://localhost:8101")
public interface UserFeignClient {

	@GetMapping("/api/v2/users")
	public List<User> getAllUsers();

	@GetMapping("/api/v2/users/{user_id}")
	public User getUserById(@PathVariable("user_id") Integer userId);
}
