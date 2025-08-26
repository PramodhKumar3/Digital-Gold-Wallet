package com.cg.gold.address.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class PhysicalGoldTransactionDTO {

	private Integer transactionId;

	@NotNull(message = "User is required")
	private UserDTO user;

	@NotNull(message = "Vendor branch is required")
	private VendorBranchDTO branch;

	private Double quantity;

	private AddressDTO deliveryAddress;

	@PastOrPresent(message = "CreatedAt must be in the past or present")
	private LocalDateTime createdAt;

	public PhysicalGoldTransactionDTO() {
	}

	public PhysicalGoldTransactionDTO(Integer transactionId, UserDTO user, VendorBranchDTO branch, Double quantity,
			AddressDTO deliveryAddress, LocalDateTime createdAt) {
		this.transactionId = transactionId;
		this.user = user;
		this.branch = branch;
		this.quantity = quantity;
		this.deliveryAddress = deliveryAddress;
		this.createdAt = createdAt;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public VendorBranchDTO getBranch() {
		return branch;
	}

	public void setBranch(VendorBranchDTO branch) {
		this.branch = branch;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public AddressDTO getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(AddressDTO deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
