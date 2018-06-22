package com.example.pitangua.psystem.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.pitangua.psystem.domain.User;
import com.example.pitangua.psystem.service.IClinicService;
import com.example.pitangua.psystem.service.IUserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private IUserService userService;

	@Autowired
	private IClinicService clinicService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

		// Name validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "Name is required.");

		// Email validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required.email", "Email is required.");
		if (userService.getByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "field.duplicate", "This email is already taken.");
		}

		// CPF validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "field.required", "CPF is required.");
		if (userService.getByCpf(user.getCpf()) != null) {
			errors.rejectValue("cpf", "field.duplicate", "This CPF is already taken.");
		}

		// CRP validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "crp", "field.required", "CRP is required.");
		if (userService.getByCrp(user.getCrp()) != null) {
			errors.rejectValue("crp", "field.duplicate", "This CRP is already taken.");
		}

		// Password validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required", "Password is required.");
		if (user.getPassword().length() < 6) {
			errors.rejectValue("password", "field.size", "The password must contain at least 6 characters.");
		}
		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "field.match", "Passwords don't match");
		}
	}

}
