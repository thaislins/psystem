package com.example.pitangua.psystem.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public UserDetails getUserDetails() {
		Authentication auth = getAuthentication();
		return auth != null ? (UserDetails) auth.getPrincipal() : null;
	}

}
