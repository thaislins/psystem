package com.example.pitangua.psystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.pitangua.psystem.domain.ScheduleAppointment;
import com.example.pitangua.psystem.exception.UnhandledException;

public class ScheduleAppointmentDAO extends GenericDAO<ScheduleAppointment> {

	@Override
	public void insert(ScheduleAppointment entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(ScheduleAppointment entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ScheduleAppointment entity) {
		// TODO Auto-generated method stub
		
	}
	
	public Integer getAppointmentCount() {
		return getAllAppointments().size();
	}

	public List<ScheduleAppointment> getAllAppointments() {
		List<ScheduleAppointment> appointments = new ArrayList<>();

		String sql = "SELECT * FROM schedule_appointment";
		try (PreparedStatement stmt = ConnectionManager.getConnection().prepareStatement(sql);
				ResultSet resultSet = stmt.executeQuery();) {
			while (resultSet.next()) {
				appointments.add(fromResultSet(resultSet));
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return appointments;
	}

	private ScheduleAppointment fromResultSet(ResultSet rs) throws SQLException {		
		Integer id = rs.getInt("id");
		Integer psychologist_id = rs.getInt("psychologist_id");
		Integer client_id = rs.getInt("client_id");
		Date date = rs.getDate("date");
		
		return new ScheduleAppointment(id,psychologist_id,client_id,date);
	}
}
