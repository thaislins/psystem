package com.example.pitangua.psystem.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.pitangua.psystem.domain.CepAddress;

@Component
public class CepAddressValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CepAddress.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		CepAddress cepAddress = (CepAddress) obj;

		// CEP validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cep", "field.required.cep", "CEP is required.");

		// Street validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "field.required.street", "Street is required.");

		// City validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "field.required.city", "City is required.");
		if (cepAddress.getCep().length() != 8) {
			errors.rejectValue("cep", "field.length.cep", "Invalid CEP.");
		}

		// State validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "field.required.state", "State is required.");
		if (cepAddress.getState().length() != 2) {
			errors.rejectValue("state", "field.length.state", "Invalid State.");
		}
	}

}
