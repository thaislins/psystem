package com.example.pitangua.psystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.pitangua.psystem.domain.User;
import com.example.pitangua.psystem.service.IUserService;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

	@Autowired
	private IUserService userService;

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public UserDetails getUserDetails() {
		Authentication auth = getAuthentication();
		return auth != null ? (UserDetails) auth.getPrincipal() : null;
	}

	@Override
	public User getUser() {
		UserDetails ud = getUserDetails();

		if (ud != null) {
			return userService.getByEmail(ud.getUsername());
		}

		return null;
	}

}
