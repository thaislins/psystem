package com.example.pitangua.psystem.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.pitangua.psystem.domain.Client;
import com.example.pitangua.psystem.service.IClientService;
import com.example.pitangua.psystem.service.IUserService;

@Component
public class ClientValidator implements Validator {

	@Autowired
	private IUserService userService;

	@Autowired
	private IClientService clientService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Client.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Client client = (Client) obj;

		// Name validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "Name is required.");

		// CPF validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "field.required", "CPF is required.");
		if (clientService.getByCpf(client.getCpf()) != null) {
			errors.rejectValue("cpf", "field.duplicate", "This CPF is already taken.");
		}

		// Birth Date validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "field.required", "Birth is required.");

		// Occupation Validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "occupation", "field.required", "Ocuupation is required.");

		// Phone Validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "field.required", "Phone Number is required.");

		// Number Validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number", "field.required", "Phone Number is required.");
	}

}
