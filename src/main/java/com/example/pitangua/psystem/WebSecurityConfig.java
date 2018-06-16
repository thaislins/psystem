package com.example.pitangua.psystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/assets/**", "/css/**", "/js/**", "/scss/**", "/login", "/register", "/register-clinic")
				.permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.anyRequest().fullyAuthenticated()
				.and()
				.formLogin() // Login Configuration
				.loginPage("/login")
				.loginProcessingUrl("/auth")
				.usernameParameter("email").passwordParameter("password")
				.defaultSuccessUrl("/dashboard")
				.and()
				.logout() // Logout Configuration
				.logoutUrl("/logout").logoutSuccessUrl("/login")
				.and()
				.exceptionHandling() // Exception Handling Configuration
				.accessDeniedPage("/403");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
