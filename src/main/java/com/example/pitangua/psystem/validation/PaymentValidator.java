package com.example.pitangua.psystem.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.pitangua.psystem.domain.Payment;

@Component
public class PaymentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Payment.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Payment payment = (Payment) obj;

		if (payment.getNotes().length() > 255) {
			errors.rejectValue("notes", "field.length.notes", "Invalid Notes.");
		}
	}

}
