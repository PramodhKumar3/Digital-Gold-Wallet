package com.cg.gold.payment.service;

import java.util.List;

import com.cg.gold.payment.entity.Payment;
import com.cg.gold.payment.entity.Payment.PaymentMethod;
import com.cg.gold.payment.exception.PaymentException;

public interface PaymentService {

	public List<Payment> getAllPayments();

	public Payment getPaymentById(Integer paymentId) throws PaymentException;

	public List<Payment> getAllPaymentByUserId(Integer userId) throws PaymentException;

	public List<Payment> getAllSuccessPayments();

	public List<Payment> getAllFailedPayments();

	public List<Payment> getAllPaymentsByPaymentMethod(PaymentMethod paymentMethod) throws PaymentException;

	public void addPayment(Payment newPayment) throws PaymentException;

	public List<Payment> getPaymentsByUser(Integer userId) throws PaymentException;
}
