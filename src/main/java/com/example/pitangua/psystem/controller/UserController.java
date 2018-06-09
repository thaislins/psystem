package com.example.pitangua.psystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.pitangua.psystem.security.IAuthenticationFacade;
import com.example.pitangua.psystem.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IAuthenticationFacade authFacade;

	@GetMapping("login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/register")
	public ModelAndView registerRequest() {
		return null;
	}

	@GetMapping("/users")
	public ModelAndView listUsers() {
		ModelAndView mv = new ModelAndView();
		UserDetails userDetails = authFacade.getUserDetails();
		mv.addObject("user", userService.getByEmail(userDetails.getUsername()));
		mv.addObject("userList", userService.getAll());
		mv.setViewName("user/list");
		return mv;
	}

}
