package com.cg.gold.payment.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class TransactionHistoryDTO {

	private Integer transactionId;

	@NotNull(message = "User is required")
	private UserDTO user;

	@NotNull(message = "Vendor branch is required")
	private VendorBranchDTO branch;

	private TransactionType transactionType;

	private TransactionStatus transactionStatus;

	private Double quantity;

	private Double amount;

	private LocalDateTime createdAt;

	public enum TransactionType {
		Buy, Sell, ConvertToPhysical
	}

	public enum TransactionStatus {
		Success, Failed
	}

	public TransactionHistoryDTO() {
	}

	public TransactionHistoryDTO(Integer transactionId, UserDTO user, VendorBranchDTO branch,
			TransactionType transactionType, TransactionStatus transactionStatus, Double quantity, Double amount,
			LocalDateTime createdAt) {
		this.transactionId = transactionId;
		this.user = user;
		this.branch = branch;
		this.transactionType = transactionType;
		this.transactionStatus = transactionStatus;
		this.quantity = quantity;
		this.amount = amount;
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

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
