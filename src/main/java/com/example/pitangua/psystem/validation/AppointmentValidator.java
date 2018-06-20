package com.example.pitangua.psystem.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.pitangua.psystem.domain.ScheduleAppointment;

@Component
public class AppointmentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ScheduleAppointment.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ScheduleAppointment scheduleAppointment = (ScheduleAppointment) obj;

		// Date validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "field.required.date", "Date is required.");
	}

}
