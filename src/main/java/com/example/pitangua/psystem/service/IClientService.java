package com.example.pitangua.psystem.service;

import java.sql.SQLException;
import java.util.List;

import com.example.pitangua.psystem.domain.Client;

public interface IClientService {

	void insert(Client client) throws SQLException;

	void update(Client client) throws SQLException;

	List<Client> getAll();

	Integer getClientCount(int psychologistId);

	Client getByCpf(String cpf);

	Client getById(int id);

}
