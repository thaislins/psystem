package com.example.pitangua.psystem.service;

import java.sql.SQLException;
import java.util.List;

import com.example.pitangua.psystem.domain.ScheduleAppointment;

public interface IAppointmentService {

	void insert(ScheduleAppointment appointment) throws SQLException;

	void update(ScheduleAppointment appointment) throws SQLException;

	void remove(ScheduleAppointment appointment) throws SQLException;

	List<ScheduleAppointment> getAll();

	Integer getAppointmentCount(int psychologistId);

	ScheduleAppointment getById(int id);

	ScheduleAppointment getByClientId(int clientId);
}
