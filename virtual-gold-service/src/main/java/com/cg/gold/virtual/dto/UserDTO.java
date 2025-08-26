package com.cg.gold.virtual.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

public class UserDTO {

	private Integer userId;

	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Name is mandatory")
	@Pattern(regexp = "[A-Za-z\\s]{2,}", message = "Name must contain alphabets and between 2 and 100 characters")
	private String name;

	@NotNull(message = "User is required")
	private AddressDTO address;

	@NotNull(message = "Balance is required")
	@DecimalMin(value = "0.0", inclusive = true, message = "Balance must be non-negative")
	private Double balance;

	@PastOrPresent(message = "CreatedAt must be in the past or present")
	private LocalDateTime createdAt;

	public UserDTO() {
	}

	public UserDTO(Integer userId, String email, String name, AddressDTO address, Double balance,
			LocalDateTime createdAt) {
		this.userId = userId;
		this.email = email;
		this.name = name;
		this.address = address;
		this.balance = balance;
		this.createdAt = createdAt;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
