package com.example.pitangua.psystem.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.pitangua.psystem.domain.CepAddress;
import com.example.pitangua.psystem.domain.Client;
import com.example.pitangua.psystem.exception.UnhandledException;

@Repository
@Transactional
public class ClientDAO extends GenericDAO<Client> {

	@Autowired
	private CepAddressDAO cepAddressDAO;

	@Override
	public void insert(Client client) throws SQLException {
		// cepAddressDAO.insert(client.getCep());
		Date date = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

		String sql = "INSERT INTO cient(psychologist_id, cpf, name, birth_date, phone, cep, number, occupation, gender, blood_type, nationality, scholarity) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
			try {
				ps.setInt(1, client.getPsychologistId());
				ps.setString(2, client.getCpf());
				ps.setString(3, client.getName());
				date = new java.sql.Date(formatter.parse(client.getBirthDate()).getTime());
				ps.setDate(4, date);
				ps.setString(5, client.getPhone());
				ps.setString(6, client.getCep().getCep());
				ps.setString(7, client.getNumber());
				ps.setString(8, client.getOccupation());
				ps.setString(9, client.getGender());
				ps.setString(10, client.getBloodType());
				ps.setString(9, client.getNationality());
				ps.setString(9, client.getScholarity());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ps.execute();
		}
	}

	@Override
	public void remove(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Client client) {
		// TODO Auto-generated method stub

	}

	public Client getByCpf(String cpf) {
		String sql = "SELECT * FROM client WHERE cpf=?";

		try (PreparedStatement ps = createPreparedStatement(ConnectionManager.getConnection(), sql, cpf);
				ResultSet resultSet = ps.executeQuery()) {
			while (resultSet.next()) {
				return fromResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return null;
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
		String birthDate = rs.getString("birth_date");
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

		Client client = new Client(psychologistId, cpf, name, birthDate, phone, cepAdress, number, occupation, gender,
				bloodType, nationality, scholarity);
		client.setId(id);

		return client;
	}
}
