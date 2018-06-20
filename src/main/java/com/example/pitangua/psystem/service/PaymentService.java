package com.example.pitangua.psystem.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pitangua.psystem.dao.PaymentDAO;
import com.example.pitangua.psystem.domain.Payment;

@Service
public class PaymentService implements IPaymentService {

	@Autowired
	private PaymentDAO paymentDAO;

	@Override
	public void insert(Payment payment) throws SQLException {
		paymentDAO.insert(payment);

	}

	@Override
	public void remove(Payment payment) throws SQLException {
		paymentDAO.remove(payment);
	}

	@Override
	public List<Payment> getClientPayments(int id) {
		return paymentDAO.getClientPayments(id);
	}

	@Override
	public Integer getPaymentsCount(int id) {
		return paymentDAO.getPaymentCount(id);
	}

}
