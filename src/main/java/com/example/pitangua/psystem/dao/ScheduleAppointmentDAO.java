package com.example.pitangua.psystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.pitangua.psystem.domain.ScheduleAppointment;
import com.example.pitangua.psystem.exception.UnhandledException;

@Repository
@Transactional
public class ScheduleAppointmentDAO extends GenericDAO<ScheduleAppointment> {

	@Override
	public void insert(ScheduleAppointment appointment) throws SQLException {
		String sql = "INSERT INTO schedule_appointment(psychologist_id, client_id, date) VALUES(?, ?, ?)";
		LocalDateTime date = null;
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
			ps.setInt(1, appointment.getPsychologistId());
			ps.setInt(2, appointment.getClientId());
			date = LocalDateTime.parse(appointment.getDate(), dateFormat);
			ps.setTimestamp(3, Timestamp.valueOf(date));
			ps.execute();
		}

	}

	@Override
	public void remove(ScheduleAppointment appointment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ScheduleAppointment appointment) {
		// TODO Auto-generated method stub

	}

	public Integer getAppointmentCount(int psychologistId) {
		int count = 0;
		String sql = "SELECT count(*) FROM schedule_appointment WHERE psychologist_id=?;";
		try (PreparedStatement ps = createPreparedStatement(ConnectionManager.getConnection(), sql, psychologistId);
				ResultSet resultSet = ps.executeQuery()) {
			while (resultSet.next()) {
				count = resultSet.getInt("count(*)");
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return count;
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
		Integer psychologistId = rs.getInt("psychologist_id");
		Integer clientId = rs.getInt("client_id");
		String date = rs.getString("date");

		return new ScheduleAppointment(id, psychologistId, clientId, date);
	}
}
