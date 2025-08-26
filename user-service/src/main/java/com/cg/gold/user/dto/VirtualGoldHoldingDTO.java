package com.cg.gold.user.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class VirtualGoldHoldingDTO {

	private Integer holdingId;

	@NotNull(message = "User is required")
	private UserDTO user;

	@NotNull(message = "Vendor branch is required")
	private VendorBranchDTO branch;

	private Double quantity;

	private LocalDateTime createdAt;

	public VirtualGoldHoldingDTO() {
	}

	public VirtualGoldHoldingDTO(Integer holdingId, UserDTO user, VendorBranchDTO branch, Double quantity,
			LocalDateTime createdAt) {
		this.holdingId = holdingId;
		this.user = user;
		this.branch = branch;
		this.quantity = quantity;
		this.createdAt = createdAt;
	}

	public Integer getHoldingId() {
		return holdingId;
	}

	public void setHoldingId(Integer holdingId) {
		this.holdingId = holdingId;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
