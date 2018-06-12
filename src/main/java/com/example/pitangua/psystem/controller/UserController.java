package com.example.pitangua.psystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.pitangua.psystem.dao.ClinicDAO;
import com.example.pitangua.psystem.dao.UserDAO;
import com.example.pitangua.psystem.domain.User;
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
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		ClinicDAO clinicDAO = new ClinicDAO();
		
		mv.addObject("clinicList", clinicDAO.getAllClinics());
		return mv;
	}
	
	@PostMapping("/register")
	public ModelAndView registerRequest(String cpf, Integer clinicId, String name, String email,
			String password, String phone, boolean admin, boolean psychologist, String crp) {
		
		ModelAndView mv = new ModelAndView("login");
		UserDAO userDAO = new UserDAO();
		
		User user = new User(cpf,clinicId,name,email,password,phone,admin,psychologist,crp);
		userDAO.insert(user);
		return mv;
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
