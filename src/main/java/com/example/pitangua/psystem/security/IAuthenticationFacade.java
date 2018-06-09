package com.example.pitangua.psystem.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthenticationFacade {

	Authentication getAuthentication();

	UserDetails getUserDetails();

}
