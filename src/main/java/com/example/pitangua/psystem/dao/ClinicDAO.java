package com.example.pitangua.psystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.pitangua.psystem.domain.CepAddress;
import com.example.pitangua.psystem.domain.Clinic;
import com.example.pitangua.psystem.exception.UnhandledException;

@Repository
@Transactional
public class ClinicDAO extends GenericDAO<Clinic> {

	@Autowired
	private CepAddressDAO cepAddressDAO;

	@Override
	public void insert(Clinic entity) throws SQLException {
		cepAddressDAO.insert(entity.getCep());

		String sql = "INSERT INTO clinic(name, phone, cep, number) VALUES(?, ?, ?, ?)";
		try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getPhone());
			ps.setString(3, entity.getCep().getCep());
			ps.setString(4, entity.getNumber());
			ps.execute();
		}
	}

	@Override
	public void remove(Clinic entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Clinic entity) throws SQLException {
		cepAddressDAO.update(entity.getCep());

		String sql = "UPDATE clinic SET name=?, phone=?, cep=?, number=? WHERE id=?";
		try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getPhone());
			ps.setString(3, entity.getCep().getCep());
			ps.setString(4, entity.getNumber());
			ps.setInt(5, entity.getId());
			ps.execute();
		}
	}

	public Clinic getById(Integer id) {
		String sql = "SELECT * FROM clinic NATURAL JOIN cep_address WHERE id=?";

		try (PreparedStatement ps = createPreparedStatement(ConnectionManager.getConnection(), sql, id);
				ResultSet resultSet = ps.executeQuery()) {
			while (resultSet.next()) {
				return fromResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return null;
	}

	public List<Clinic> getAll() {
		List<Clinic> clinics = new ArrayList<>();

		String sql = "SELECT * FROM clinic NATURAL JOIN cep_address";
		try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);
				ResultSet resultSet = ps.executeQuery();) {
			while (resultSet.next()) {
				clinics.add(fromResultSet(resultSet));
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return clinics;
	}

	private Clinic fromResultSet(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String phone = rs.getString("phone");
		String cep = rs.getString("cep");
		String street = rs.getString("street");
		String city = rs.getString("city");
		String state = rs.getString("state");
		String number = rs.getString("number");

		CepAddress cepAdress = new CepAddress(cep, street, city, state);

		return new Clinic(id, name, phone, cepAdress, number);
	}

}
