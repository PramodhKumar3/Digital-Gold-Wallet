package com.cg.gold.goldfeign.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.gold.goldfeign.dto.UserDTO;
import com.cg.gold.goldfeign.service.UserQueryService;

@RestController
@RequestMapping("/api/v2/feign/users")
public class UserQueryController {

	@Autowired
	private UserQueryService userQueryService;

	@GetMapping("/search/recent/{days}")
	public List<UserDTO> getRecentUsers(@PathVariable int days) {
		return userQueryService.findUsersCreatedInLastNDays(days);
	}

	@GetMapping("/search/by-city-balance/{city}/{minimum}/{maximum}")
	public List<UserDTO> getUsersByCityAndBalance(@PathVariable String city, @PathVariable("minimum") Double minBalance,
			@PathVariable("maximum") Double maxBalance) {
		return userQueryService.findUsersByCityAndBalance(city, minBalance, maxBalance);
	}

	@GetMapping("/stats/average-balance")
	public Map<String, Double> getAverageBalanceByCity() {
		return userQueryService.getAverageBalanceByCity();
	}
}
