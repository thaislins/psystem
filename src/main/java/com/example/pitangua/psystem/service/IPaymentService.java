package com.example.pitangua.psystem.service;

import java.sql.SQLException;
import java.util.List;

import com.example.pitangua.psystem.domain.Payment;

public interface IPaymentService {

	void insert(Payment anamnesis) throws SQLException;

	void remove(Payment anamnesis) throws SQLException;

	List<Payment> getClientPayments(int id);

	Integer getPaymentsCount(int id);

}
