package com.example.pitangua.psystem.service;

import java.sql.SQLException;
import java.util.List;

import com.example.pitangua.psystem.domain.User;

public interface IUserService {

	void insert(User user) throws SQLException;

	void update(User client) throws SQLException;

	void remove(User client) throws SQLException;

	List<User> getAll();

	User getByEmail(String email);

	User getByCpf(String cpf);

	User getById(int id);

	User getByCrp(String crp);

	List<User> getByClinic(int clinicId);

	Integer getCountByClinic(int clinicId);

}
