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

import com.example.pitangua.psystem.domain.Payment;
import com.example.pitangua.psystem.exception.UnhandledException;

@Repository
@Transactional
public class PaymentDAO extends GenericDAO<Payment> {

	@Override
	public void insert(Payment entity) throws SQLException {
		String sql = "INSERT INTO payment(psychologist_id, client_id, value, date, notes) VALUES(?, ?, ?, ?, ?)";
		LocalDateTime date = null;
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
			ps.setInt(1, entity.getPsychologistId());
			ps.setInt(2, entity.getClientId());
			ps.setInt(3, entity.getValue());
			date = LocalDateTime.parse(entity.getDate(), dateFormat);
			ps.setTimestamp(4, Timestamp.valueOf(date));
			ps.setString(5, entity.getNotes());
			ps.execute();
		}
	}

	@Override
	public void remove(Payment entity) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Payment entity) throws SQLException {
		// A payment cannot be modified
		throw new UnsupportedOperationException();
	}

	public Integer getPaymentCount(int psychologistId) {
		int count = 0;
		String sql = "SELECT count(*) FROM payment WHERE psychologist_id=?;";
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

	public List<Payment> getClientPayments(int clientId) {
		List<Payment> payments = new ArrayList<>();

		String sql = "SELECT * FROM payment p WHERE EXISTS (SELECT * FROM client c WHERE c.id=p.client_id AND p.client_id=?)";
		try (PreparedStatement ps = createPreparedStatement(ConnectionManager.getConnection(), sql, clientId);
				ResultSet resultSet = ps.executeQuery()) {
			while (resultSet.next()) {
				payments.add(fromResultSet(resultSet));
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return payments;
	}

	private Payment fromResultSet(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("id");
		Integer psychologistId = rs.getInt("psychologist_id");
		Integer clientId = rs.getInt("client_id");
		Integer value = rs.getInt("value");
		String date = rs.getString("date");
		String notes = rs.getString("notes");

		return new Payment(id, psychologistId, clientId, value, date, notes);
	}

}
