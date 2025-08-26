package com.cg.gold.payment.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.gold.payment.dto.UserDTO;
import com.cg.gold.payment.entity.Payment;
import com.cg.gold.payment.entity.Payment.PaymentMethod;
import com.cg.gold.payment.entity.Payment.PaymentStatus;
import com.cg.gold.payment.exception.PaymentException;
import com.cg.gold.payment.feign.UserFeignClient;
import com.cg.gold.payment.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private UserFeignClient userFeignClient;

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	@Override
	public Payment getPaymentById(Integer paymentId) throws PaymentException {
		return paymentRepository.findById(paymentId)
				.orElseThrow(() -> new PaymentException("PaymentService.PAYMENT_NOT_FOUND"));
	}

	@Override
	public List<Payment> getAllPaymentByUserId(Integer userId) throws PaymentException {
		UserDTO userDTO = userFeignClient.getUserById(userId);
		if (userDTO == null || userDTO.getUserId() == null)
			throw new PaymentException("UserService.USER_NOT_FOUND");
		List<Payment> payments = paymentRepository.findByUserUserId(userId);
		if (payments.isEmpty()) {
			throw new PaymentException("PaymentService.USER_NOT_FOUND");
		}
		return payments;
	}

	@Override
	public List<Payment> getAllSuccessPayments() {
		return paymentRepository.findByPaymentStatus(PaymentStatus.Success);
	}

	@Override
	public List<Payment> getAllFailedPayments() {
		return paymentRepository.findByPaymentStatus(PaymentStatus.Failed);
	}

	@Override
	public List<Payment> getAllPaymentsByPaymentMethod(PaymentMethod paymentMethod) throws PaymentException {
		List<PaymentMethod> validMethods = Arrays.asList(PaymentMethod.AmazonPay, PaymentMethod.BankTransfer,
				PaymentMethod.CreditCard, PaymentMethod.DebitCard, PaymentMethod.GooglePay, PaymentMethod.Paytm,
				PaymentMethod.PhonePe);
		if (!validMethods.contains(paymentMethod))
			throw new PaymentException("PaymentService.INVALID_PAYMENT");
		List<Payment> payments = paymentRepository.findByPaymentMethod(paymentMethod);
		if (payments.isEmpty())
			throw new PaymentException("PaymentService.NO_PAYMENT_AVAILABLE");
		return payments;
	}

	@Override
	public void addPayment(Payment newPayment) throws PaymentException {
		Integer userId = newPayment.getUser().getUserId();
		UserDTO userDTO = userFeignClient.getUserById(userId);
		if (userDTO == null || userDTO.getUserId() == null)
			throw new PaymentException("UserService.USER_NOT_FOUND");
		newPayment.setCreatedAt(LocalDateTime.now());
		paymentRepository.save(newPayment);
	}

	@Override
	public List<Payment> getPaymentsByUser(Integer userId) throws PaymentException {
		UserDTO userDTO = userFeignClient.getUserById(userId);
		if (userDTO == null || userDTO.getUserId() == null)
			throw new PaymentException("UserService.USER_NOT_FOUND");
		return paymentRepository.findByUserUserIdOrderByCreatedAtDesc(userId);
	}

}
