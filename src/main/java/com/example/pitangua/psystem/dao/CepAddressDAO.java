package com.example.pitangua.psystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.pitangua.psystem.domain.CepAddress;
import com.example.pitangua.psystem.exception.UnhandledException;;

public class CepAddressDAO extends GenericDAO<CepAddress> {

	@Override
	public void insert(CepAddress entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void remove(CepAddress entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(CepAddress entity) {
		// TODO Auto-generated method stub
	}

	public CepAddress getByCep(String cep) {
		String sql = "SELECT * FROM cep_address WHERE cep=?";
		ResultSet resultSet = null;

		try (PreparedStatement stmt = ConnectionManager.getConnection().prepareStatement(sql)) {
			stmt.setString(1, cep);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				return fromResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new UnhandledException("DB Error", e);
				}
			}
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
