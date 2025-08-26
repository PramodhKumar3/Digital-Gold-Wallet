package com.cg.gold.user.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class VendorBranchDTO {

	private Integer branchId;

	@NotNull(message = "Vendor is required")
	private VendorDTO vendor;

	private AddressDTO address;

	@NotNull
	private Double quantity;

	@PastOrPresent(message = "CreatedAt must be in the past or present")
	private LocalDateTime createdAt;

	public VendorBranchDTO() {
	}

	public VendorBranchDTO(Integer branchId, VendorDTO vendor, AddressDTO address, Double quantity,
			LocalDateTime createdAt) {
		this.branchId = branchId;
		this.vendor = vendor;
		this.address = address;
		this.quantity = quantity;
		this.createdAt = createdAt;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public VendorDTO getVendor() {
		return vendor;
	}

	public void setVendor(VendorDTO vendor) {
		this.vendor = vendor;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
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
