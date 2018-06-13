package com.example.pitangua.psystem.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pitangua.psystem.dao.UserDAO;
import com.example.pitangua.psystem.domain.User;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public void insert(User user) throws SQLException {
		userDAO.insert(user);
	}

	@Override
	public List<User> getAll() {
		return userDAO.getAllUsers();
	}

	@Override
	public User getByEmail(String email) {
		return userDAO.getByEmail(email);
	}

	@Override
	public User getByCpf(String cpf) {
		return userDAO.getByCpf(cpf);
	}

	@Override
	public User getByCrp(String crp) {
		return userDAO.getByCrp(crp);
	}

}
