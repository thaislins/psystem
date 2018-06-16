package com.example.pitangua.psystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.pitangua.psystem.domain.User;
import com.example.pitangua.psystem.security.IAuthenticationFacade;
import com.example.pitangua.psystem.service.IUserService;

@Controller
public class ClientController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IAuthenticationFacade authFacade;

	@GetMapping("/clients/register")
	public ModelAndView registerClient() {
		ModelAndView mv = new ModelAndView("register-client");
		User user = authFacade.getUser();
		mv.addObject("user", user);
		mv.addObject("userList", userService.getAll());
		return mv;
	}
}
