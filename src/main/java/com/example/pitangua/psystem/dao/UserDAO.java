package com.example.pitangua.psystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.pitangua.psystem.domain.User;
import com.example.pitangua.psystem.exception.UnhandledException;

@Repository
@Transactional
public class UserDAO extends GenericDAO<User> {

	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
	}

	@Override
	public void remove(User user) {
		// TODO Auto-generated method stub
	}

	@Override
	public void find(User user) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
	}

	public User getByEmail(String email) {
		String sql = "SELECT * FROM user WHERE email=?";
		ResultSet resultSet = null;

		try (PreparedStatement stmt = ConnectionManager.getConnection().prepareStatement(sql);) {
			stmt.setString(1, email);
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

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();

		String sql = "SELECT * FROM user";
		try (PreparedStatement stmt = ConnectionManager.getConnection().prepareStatement(sql);
				ResultSet resultSet = stmt.executeQuery();) {
			while (resultSet.next()) {
				users.add(fromResultSet(resultSet));
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return users;
	}

	private User fromResultSet(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("id");
		String cpf = rs.getString("cpf");
		Integer clinicId = rs.getInt("clinic_id");
		String name = rs.getString("name");
		String email = rs.getString("email");
		String password = rs.getString("password");
		String phone = rs.getString("phone");
		Boolean admin = rs.getBoolean("ADMIN");
		Boolean psychologist = rs.getBoolean("PSYCHOLOGIST");
		String crp = rs.getString("crp");

		return new User(id, cpf, clinicId, name, email, password, phone, admin, psychologist, crp);
	}

}