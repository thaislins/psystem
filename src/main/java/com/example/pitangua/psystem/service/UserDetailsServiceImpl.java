package com.example.pitangua.psystem.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.pitangua.psystem.dao.UserDAO;
import com.example.pitangua.psystem.domain.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String email) {
		User user = userDAO.getByEmail(email);

		Set<GrantedAuthority> grantedAuth = new HashSet<>();
		if (user.isAdmin()) {
			grantedAuth.add(new SimpleGrantedAuthority("ADMIN"));
		}
		if (user.isPsychologist()) {
			grantedAuth.add(new SimpleGrantedAuthority("PSYCHOLOGIST"));
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuth);
	}

}
