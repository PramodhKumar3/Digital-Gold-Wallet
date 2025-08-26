package com.cg.gold.payment.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class VendorDTO {

	private Integer vendorId;

	@NotBlank(message = "Vendor name is required")
	@Pattern(regexp = "[A-Za-z\\s]{2,}", message = "Name must be between 2 and 100 characters")
	private String vendorName;

	@Size(max = 500, message = "Description must be less than 500 characters")
	private String description;

	@Size(max = 100, message = "Contact person name must be less than 100 characters")
	@Pattern(regexp = "[A-Za-z\\s]{2,}", message = "Name must be between 2 and 100 characters")
	private String contactPersonName;

	@Email(message = "Please enter a valid email address")
	@Size(max = 100, message = "Email must be less than 100 characters")
	private String contactEmail;

	@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
	private String contactPhone;

	private String websiteUrl;

	@NotNull(message = "Quantity is required")
	private Double totalGoldQuantity;

	@NotNull(message = "Gold Price is required")
	private Double currentGoldPrice;

	@PastOrPresent(message = "CreatedAt must be in the past or present")
	private LocalDateTime createdAt;

	public VendorDTO() {
	}

	public VendorDTO(Integer vendorId, String vendorName, String description, String contactPersonName,
			String contactEmail, String contactPhone, String websiteUrl, Double totalGoldQuantity,
			Double currentGoldPrice, LocalDateTime createdAt) {
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.description = description;
		this.contactPersonName = contactPersonName;
		this.contactEmail = contactEmail;
		this.contactPhone = contactPhone;
		this.websiteUrl = websiteUrl;
		this.totalGoldQuantity = totalGoldQuantity;
		this.currentGoldPrice = currentGoldPrice;
		this.createdAt = createdAt;
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public Double getTotalGoldQuantity() {
		return totalGoldQuantity;
	}

	public void setTotalGoldQuantity(Double totalGoldQuantity) {
		this.totalGoldQuantity = totalGoldQuantity;
	}

	public Double getCurrentGoldPrice() {
		return currentGoldPrice;
	}

	public void setCurrentGoldPrice(Double currentGoldPrice) {
		this.currentGoldPrice = currentGoldPrice;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
