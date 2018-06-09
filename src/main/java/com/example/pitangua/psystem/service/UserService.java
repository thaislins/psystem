package com.example.pitangua.psystem.service;

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
	public List<User> getAll() {
		return userDAO.getAllUsers();
	}

	@Override
	public User getByEmail(String email) {
		return userDAO.getByEmail(email);
	}

}
