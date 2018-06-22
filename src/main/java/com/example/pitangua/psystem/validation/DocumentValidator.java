package com.example.pitangua.psystem.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.pitangua.psystem.domain.Document;

@Component
public class DocumentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Document.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Document document = (Document) obj;

		// Date validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "field.required.date", "Date is required.");
	}

}
