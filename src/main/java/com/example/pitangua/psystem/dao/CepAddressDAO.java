package com.example.pitangua.psystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.pitangua.psystem.domain.CepAddress;
import com.example.pitangua.psystem.exception.UnhandledException;;

@Repository
@Transactional
public class CepAddressDAO extends GenericDAO<CepAddress> {

	@Override
	public void insert(CepAddress entity) throws SQLException {
		upsert(entity);
	}

	@Override
	public void remove(CepAddress entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(CepAddress entity) throws SQLException {
		upsert(entity);
	}

	private void upsert(CepAddress entity) throws SQLException {
		// Try to insert, if already exists, update
		String sql = "INSERT INTO cep_address(cep, street, city, state) VALUES(?,?,?,?) ON DUPLICATE KEY UPDATE street=?, city=?, state=?";

		try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
			ps.setString(1, entity.getCep());
			ps.setString(2, entity.getStreet());
			ps.setString(3, entity.getCity());
			ps.setString(4, entity.getState());
			ps.setString(5, entity.getStreet());
			ps.setString(6, entity.getCity());
			ps.setString(7, entity.getState());
			ps.execute();
		}
	}

	public CepAddress getByCep(String cep) {
		String sql = "SELECT * FROM cep_address WHERE cep=?";

		try (PreparedStatement stmt = createPreparedStatement(ConnectionManager.getConnection(), sql, cep);
				ResultSet resultSet = stmt.executeQuery()) {
			while (resultSet.next()) {
				return fromResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return null;
	}

	private CepAddress fromResultSet(ResultSet rs) throws SQLException {
		String cep = rs.getString("cep");
		String street = rs.getString("street");
		String city = rs.getString("city");
		String state = rs.getString("state");

		return new CepAddress(cep, street, city, state);
	}
}
