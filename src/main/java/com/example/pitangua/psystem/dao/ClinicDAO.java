package com.example.pitangua.psystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.pitangua.psystem.domain.CepAdress;
import com.example.pitangua.psystem.domain.Clinic;
import com.example.pitangua.psystem.domain.User;
import com.example.pitangua.psystem.exception.UnhandledException;

public class ClinicDAO extends GenericDAO<Clinic> {

	@Override
	public void insert(Clinic entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Clinic entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(Clinic entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Clinic entity) {
		// TODO Auto-generated method stub
		
	}
	
	public Clinic getById(Integer id) {
		String sql = "SELECT * FROM clinic WHERE name=?";
		ResultSet resultSet = null;

		try (PreparedStatement stmt = ConnectionManager.getConnection().prepareStatement(sql)) {
			stmt.setInt(1, id);
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
	
	public List<Clinic> getAllClinics() {
		List<Clinic> clinics = new ArrayList<>();

		String sql = "SELECT * FROM clinic";
		try (PreparedStatement stmt = ConnectionManager.getConnection().prepareStatement(sql);
				ResultSet resultSet = stmt.executeQuery();) {
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
		Integer number = rs.getInt("number");

		CepAdressDAO cepAdressDAO = new CepAdressDAO();
		CepAdress cepAdress = cepAdressDAO.getByCep(cep);
		
		return new Clinic(id,name,phone,cepAdress,number);
	}
}
