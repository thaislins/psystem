package com.example.pitangua.psystem.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.pitangua.psystem.domain.AppointmentNote;

@Repository
@Transactional
public class AppointmentNoteDAO extends GenericDAO<AppointmentNote> {

	@Autowired
	private ScheduleAppointmentDAO appointmentDAO;

	@Override
	public void insert(AppointmentNote note) throws SQLException {
		appointmentDAO.insert(note.getAppointment());

		String sql = "INSERT INTO appointment_notes(notes,date) VALUES(?, ?)";
		LocalDateTime date = null;
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
			ps.setString(1, note.getNotes());
			date = LocalDateTime.parse(note.getDate(), dateFormat);
			ps.setTimestamp(2, Timestamp.valueOf(date));
			ps.execute();
		}

		sql = "INSERT INTO schedule_appointment_notes(sched_appt_id,appt_notes_id) VALUES(?, ?)";
		try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
			ps.setInt(1, note.getId());
			ps.setInt(2, note.getAppointment().getId());
			ps.execute();
		}

	}

	@Override
	public void remove(AppointmentNote note) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AppointmentNote note) throws SQLException {
		// TODO Auto-generated method stub

	}

	/*
	 * public List<ScheduleAppointment> getAllNotes(int psychologistId) {
	 * List<ScheduleAppointment> appointments = new ArrayList<>();
	 * 
	 * String sql = "SELECT * FROM schedule_appointment WHERE psychologist_id=?;";
	 * try (PreparedStatement ps =
	 * createPreparedStatement(ConnectionManager.getConnection(), sql,
	 * psychologistId); ResultSet resultSet = ps.executeQuery()) { while
	 * (resultSet.next()) { appointments.add(fromResultSet(resultSet)); } } catch
	 * (SQLException e) { throw new UnhandledException("DB Error", e); }
	 * 
	 * return appointments; }
	 */

}
