package com.example.pitangua.psystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.pitangua.psystem.domain.CepAddress;
import com.example.pitangua.psystem.domain.Client;
import com.example.pitangua.psystem.exception.UnhandledException;

public class ClientDAO extends GenericDAO<Client> {

	@Override
	public void insert(Client entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Client entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Client entity) {
		// TODO Auto-generated method stub

	}

	public Integer getClientCount(int psychologistId) {
		int count = 0;
		String sql = "SELECT count(*) FROM client WHERE psychologist_id=?";
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

	public List<Client> getAllClients() {
		List<Client> clients = new ArrayList<>();

		String sql = "SELECT * FROM client";
		try (PreparedStatement stmt = ConnectionManager.getConnection().prepareStatement(sql);
				ResultSet resultSet = stmt.executeQuery();) {
			while (resultSet.next()) {
				clients.add(fromResultSet(resultSet));
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return clients;
	}

	private Client fromResultSet(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("id");
		Integer psychologistId = rs.getInt("psychologist_id");
		String cpf = rs.getString("cpf");
		String name = rs.getString("name");
		Date birthDate = rs.getDate("birth_date");
		String phone = rs.getString("phone");
		String cep = rs.getString("cep");
		String number = rs.getString("number");
		String occupation = rs.getString("occupation");
		String gender = rs.getString("gender");
		String bloodType = rs.getString("blood_type");
		String nationality = rs.getString("nationality");
		String scholarity = rs.getString("scholarity");

		CepAddressDAO cepAdressDAO = new CepAddressDAO();
		CepAddress cepAdress = cepAdressDAO.getByCep(cep);

		return new Client(id, psychologistId, cpf, name, birthDate, phone, cepAdress, number, occupation, gender,
				bloodType, nationality, scholarity);
	}
}
