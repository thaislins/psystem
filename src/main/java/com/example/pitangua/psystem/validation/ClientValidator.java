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
		Client cc = clientService.getByCpf(client.getCpf());
		if (cc != null && cc.getId() != client.getId()) {
			errors.rejectValue("cpf", "field.duplicate", "This CPF is already taken.");
		}

		// Phone Validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "field.required", "Phone Number is required.");

	}

}
