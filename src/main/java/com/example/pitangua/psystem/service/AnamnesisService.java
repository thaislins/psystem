package com.example.pitangua.psystem.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pitangua.psystem.dao.AnamnesisDAO;
import com.example.pitangua.psystem.domain.Anamnesis;

@Service
public class AnamnesisService implements IAnamnesisService {

	@Autowired
	private AnamnesisDAO anamnesisDAO;

	@Override
	public void insert(Anamnesis anamnesis) throws SQLException {
		anamnesisDAO.insert(anamnesis);
	}

	@Override
	public void update(Anamnesis anamnesis) throws SQLException {
		anamnesisDAO.update(anamnesis);
	}

	@Override
	public void remove(Anamnesis anamnesis) throws SQLException {
		anamnesisDAO.remove(anamnesis);
	}

	@Override
	public Anamnesis getByClientId(int id) {
		return anamnesisDAO.getByClientId(id);
	}

}
