package com.example.pitangua.psystem.service;

import java.sql.SQLException;

import com.example.pitangua.psystem.domain.Anamnesis;

public interface IAnamnesisService {

	void insert(Anamnesis anamnesis) throws SQLException;

	void update(Anamnesis anamnesis) throws SQLException;

	void remove(Anamnesis anamnesis) throws SQLException;

	Anamnesis getByClientId(int id);

}
