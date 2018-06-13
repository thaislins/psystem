package com.example.pitangua.psystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.pitangua.psystem.domain.User;
import com.example.pitangua.psystem.exception.UnhandledException;

@Repository
@Transactional
public class UserDAO extends GenericDAO<User> {

	@Override
	public void insert(User user) throws SQLException {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String sql = "INSERT INTO user(cpf, clinic_id, name, email, password, phone, ADMIN, PSYCHOLOGIST, crp)" +
				"VALUES(?,?,?,?,?,?,?,?,?);";

		try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
			ps.setString(1, user.getCpf());
			ps.setInt(2, user.getClinicId());
			ps.setString(3, user.getName());
			ps.setString(4, user.getEmail());
			ps.setString(5, bcrypt.encode(user.getPassword()));
			ps.setString(6, user.getPhone());
			ps.setBoolean(7, user.isAdmin());
			ps.setBoolean(8, user.isPsychologist());
			ps.setString(9, user.getCrp());
			ps.execute();
		}
	}

	@Override
	public void remove(User user) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
	}

	public User getByEmail(String email) {
		String sql = "SELECT * FROM user WHERE email=?";

		try (PreparedStatement ps = createPreparedStatement(ConnectionManager.getConnection(), sql, email);
				ResultSet resultSet = ps.executeQuery()) {
			while (resultSet.next()) {
				return fromResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return null;
	}

	public User getByCrp(String crp) {
		String sql = "SELECT * FROM user WHERE crp=?";

		try (PreparedStatement ps = createPreparedStatement(ConnectionManager.getConnection(), sql, crp);
				ResultSet resultSet = ps.executeQuery()) {
			while (resultSet.next()) {
				return fromResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return null;
	}

	public User getByCpf(String cpf) {
		String sql = "SELECT * FROM user WHERE cpf=?";

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

	public List<User> getByClinic(int clinic) {
		List<User> users = new ArrayList<>();

		String sql = "SELECT * FROM user WHERE clinic_id=?";
		try (PreparedStatement ps = createPreparedStatement(ConnectionManager.getConnection(), sql, clinic);
				ResultSet resultSet = ps.executeQuery()) {
			while (resultSet.next()) {
				users.add(fromResultSet(resultSet));
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return users;
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();

		String sql = "SELECT * FROM user";
		try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);
				ResultSet resultSet = ps.executeQuery()) {
			while (resultSet.next()) {
				users.add(fromResultSet(resultSet));
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return users;
	}

	public Integer getPsychologistCount() {
		int count = 0;

		String sql = "SELECT count(*) FROM user WHERE PSYCHOLOGIST=true;";
		try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);
				ResultSet resultSet = ps.executeQuery()) {
			while (resultSet.next()) {
				count = resultSet.getInt("count(*)");
			}
		} catch (SQLException e) {
			throw new UnhandledException("DB Error", e);
		}

		return count;
	}

	private User fromResultSet(ResultSet rs) throws SQLException {
		String cpf = rs.getString("cpf");
		Integer clinicId = rs.getInt("clinic_id");
		String name = rs.getString("name");
		String email = rs.getString("email");
		String password = rs.getString("password");
		String phone = rs.getString("phone");
		Boolean admin = rs.getBoolean("ADMIN");
		Boolean psychologist = rs.getBoolean("PSYCHOLOGIST");
		String crp = rs.getString("crp");

		return new User(cpf, clinicId, name, email, password, phone, admin, psychologist, crp);
	}

}
