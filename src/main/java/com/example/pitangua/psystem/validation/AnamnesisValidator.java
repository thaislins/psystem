package com.example.pitangua.psystem.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.pitangua.psystem.domain.Anamnesis;

@Component
public class AnamnesisValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Anamnesis.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Anamnesis anamnesis = (Anamnesis) obj;

		if (anamnesis.getHumor().length() > 255) {
			errors.rejectValue("humor", "field.length.humor", "Invalid Humor.");
		}
	}

}
