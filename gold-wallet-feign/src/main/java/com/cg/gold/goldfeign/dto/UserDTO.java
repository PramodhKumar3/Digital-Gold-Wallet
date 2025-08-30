package com.cg.gold.goldfeign.dto;

import java.time.LocalDateTime;

import com.cg.gold.goldfeign.entity.User;

public class UserDTO {

	private Integer userId;
	private String name;
	private String email;
	private Double balance;
	private String city;
	private LocalDateTime createdAt;

	public UserDTO() {
	}

	public UserDTO(User user) {
		this.userId = user.getUserId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.balance = user.getBalance();
		this.city = user.getAddress().getCity();
		this.createdAt = user.getCreatedAt();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
