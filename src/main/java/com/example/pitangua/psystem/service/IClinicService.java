package com.example.pitangua.psystem.service;

import java.sql.SQLException;
import java.util.List;

import com.example.pitangua.psystem.domain.Clinic;

public interface IClinicService {

	void insert(Clinic clinic) throws SQLException;

	void update(Clinic clinic) throws SQLException;

	List<Clinic> getAll();

	Clinic getById(int id);

}
