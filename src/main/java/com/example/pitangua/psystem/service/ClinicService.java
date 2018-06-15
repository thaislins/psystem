package com.example.pitangua.psystem.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pitangua.psystem.dao.ClinicDAO;
import com.example.pitangua.psystem.domain.Clinic;

@Service
public class ClinicService implements IClinicService {

	@Autowired
	private ClinicDAO clinicDAO;

	@Override
	public void update(Clinic clinic) throws SQLException {
		clinicDAO.update(clinic);
	}

	@Override
	public List<Clinic> getAll() {
		return clinicDAO.getAll();
	}

	@Override
	public Clinic getById(int id) {
		return clinicDAO.getById(id);
	}

}
