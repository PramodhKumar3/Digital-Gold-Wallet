package com.cg.gold.vendorbranch.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class PaymentDTO {

	private Integer paymentId;

	@NotNull(message = "User is required")
	private UserDTO user;

	@NotNull(message = "Amount is required")
	private Double amount;

	@NotNull(message = "Payment method is required")
	private PaymentMethod paymentMethod;

	private TransactionType transactionType;

	@NotNull(message = "Payment status is required")
	private PaymentStatus paymentStatus;

	@PastOrPresent(message = "CreatedAt must be in the past or present")
	private LocalDateTime createdAt;

	public enum PaymentMethod {
		CreditCard, DebitCard, GooglePay, AmazonPay, PhonePe, Paytm, BankTransfer
	}

	public enum TransactionType {
		CreditedToWallet, DebitedFromWallet
	}

	public enum PaymentStatus {
		Success, Failed
	}

	public PaymentDTO() {
	}

	public PaymentDTO(Integer paymentId, UserDTO user, Double amount, PaymentMethod paymentMethod,
			TransactionType transactionType, PaymentStatus paymentStatus, LocalDateTime createdAt) {
		this.paymentId = paymentId;
		this.user = user;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.transactionType = transactionType;
		this.paymentStatus = paymentStatus;
		this.createdAt = createdAt;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
