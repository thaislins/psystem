package com.example.pitangua.psystem.service;

import java.util.List;

import com.example.pitangua.psystem.domain.Clinic;

public interface IClinicService {

	List<Clinic> getAll();

	Clinic getById(int id);

}
