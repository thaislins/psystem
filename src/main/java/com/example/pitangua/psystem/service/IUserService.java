package com.example.pitangua.psystem.service;

import java.util.List;

import com.example.pitangua.psystem.domain.User;

public interface IUserService {

	List<User> getAll();

	User getByEmail(String email);

}
