package com.example.pitangua.psystem.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.pitangua.psystem.domain.Clinic;

@Component
public class ClinicValidator implements Validator {

	@Autowired
	private CepAddressValidator cepAddressValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return Clinic.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Clinic clinic = (Clinic) obj;

		// Clinic Name validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required.name", "Name is required.");

		// Clinic Phone validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "field.required.phone", "Phone is required.");

		try {
			errors.pushNestedPath("cep");
			ValidationUtils.invokeValidator(cepAddressValidator, clinic.getCep(), errors);
		} finally {
			errors.popNestedPath();
		}
	}

}
