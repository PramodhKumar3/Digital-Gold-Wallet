package com.cg.gold.goldfeign.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.gold.goldfeign.dto.UserDTO;
import com.cg.gold.goldfeign.entity.User;
import com.cg.gold.goldfeign.feign.GoldWalletFeignClient;

@Service
public class UserQueryService {

	@Autowired
	private GoldWalletFeignClient goldWalletFeignClient;

	public List<UserDTO> findUsersCreatedInLastNDays(int days) {
		LocalDateTime cutoff = LocalDateTime.now().minusDays(days);
		return goldWalletFeignClient.getAllUsers().stream().filter(user -> user.getCreatedAt().isAfter(cutoff))
				.map(UserDTO::new).collect(Collectors.toList());
	}

	public List<UserDTO> findUsersByCityAndBalance(String city, double min, double max) {
		return goldWalletFeignClient.getAllUsers().stream()
				.filter(user -> user.getAddress().getCity().equalsIgnoreCase(city))
				.filter(user -> user.getBalance() >= min && user.getBalance() <= max).map(UserDTO::new)
				.collect(Collectors.toList());
	}

	public Map<String, Double> getAverageBalanceByCity() {
		return goldWalletFeignClient.getAllUsers().stream().collect(Collectors
				.groupingBy(user -> user.getAddress().getCity(), Collectors.averagingDouble(User::getBalance)));
	}
}
