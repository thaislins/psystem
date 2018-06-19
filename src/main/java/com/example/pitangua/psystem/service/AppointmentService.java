package com.example.pitangua.psystem.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pitangua.psystem.dao.ScheduleAppointmentDAO;
import com.example.pitangua.psystem.domain.ScheduleAppointment;

@Service
public class AppointmentService implements IAppointmentService {

	@Autowired
	private ScheduleAppointmentDAO appointmentDAO;

	@Override
	public void insert(ScheduleAppointment appointment) throws SQLException {
		appointmentDAO.insert(appointment);
	}

	@Override
	public void update(ScheduleAppointment appointment) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(ScheduleAppointment appointment) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ScheduleAppointment> getAll(int psychologistId) {
		return appointmentDAO.getAllAppointments(psychologistId);
	}

	@Override
	public Integer getAppointmentCount(int psychologistId) {
		return appointmentDAO.getAppointmentCount(psychologistId);
	}

	@Override
	public ScheduleAppointment getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduleAppointment getByClientId(int clientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
