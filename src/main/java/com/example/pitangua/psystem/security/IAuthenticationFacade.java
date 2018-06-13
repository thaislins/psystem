package com.example.pitangua.psystem.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.pitangua.psystem.domain.User;

public interface IAuthenticationFacade {

	Authentication getAuthentication();

	UserDetails getUserDetails();

	User getUser();

}
