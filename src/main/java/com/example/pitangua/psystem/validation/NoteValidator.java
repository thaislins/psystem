package com.example.pitangua.psystem.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.pitangua.psystem.domain.AppointmentNote;

@Component
public class NoteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AppointmentNote.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		AppointmentNote notes = (AppointmentNote) obj;

		// Date validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "notes", "field.required.notes", "Note is required.");
	}

}
