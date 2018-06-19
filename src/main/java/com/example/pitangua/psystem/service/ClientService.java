package com.example.pitangua.psystem.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pitangua.psystem.dao.ClientDAO;
import com.example.pitangua.psystem.domain.Client;

@Service
public class ClientService implements IClientService {

	@Autowired
	private ClientDAO clientDAO;

	@Override
	public void insert(Client client) throws SQLException {
		clientDAO.insert(client);
	}

	@Override
	public void update(Client client) throws SQLException {
		clientDAO.update(client);
	}

	@Override
	public void remove(Client client) throws SQLException {
		clientDAO.remove(client);
	}

	@Override
	public List<Client> getAll() {
		return clientDAO.getAllClients();
	}

	@Override
	public Integer getClientCount(int psychologistId) {
		return clientDAO.getClientCount(psychologistId);
	}

	@Override
	public Client getByCpf(String cpf) {
		return clientDAO.getByCpf(cpf);
	}

	@Override
	public Client getById(int id) {
		return clientDAO.getById(id);
	}

}
