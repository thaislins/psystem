package com.example.pitangua.psystem.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.pitangua.psystem.domain.User;
import com.example.pitangua.psystem.security.IAuthenticationFacade;
import com.example.pitangua.psystem.service.IClinicService;
import com.example.pitangua.psystem.service.IUserService;
import com.example.pitangua.psystem.validation.UserValidator;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IClinicService clinicService;

	@Autowired
	private IAuthenticationFacade authFacade;

	@Autowired
	private UserValidator userValidator;

	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();

		if (target == null) {
			return;
		}

		System.out.println("Target=" + target);

		if (target instanceof User) {
			dataBinder.setValidator(userValidator);
		}
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();

		User userForm = new User();

		mv.addObject("userForm", userForm);
		mv.addObject("clinicList", clinicService.getAll());

		return mv;
	}

	@PostMapping("/register")
	public String registerRequest(Model model, @ModelAttribute("userForm") @Validated User userForm,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		// Validate result
		if (result.hasErrors()) {
			model.addAttribute("clinicList", clinicService.getAll());
			return "register";
		}

		try {
			userService.insert(userForm);
		} catch (SQLException e) {
			model.addAttribute("clinicList", clinicService.getAll());
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			return "register";
		}

		redirectAttributes.addFlashAttribute("registerSuccess", true);

		return "redirect:/login";
	}

	@GetMapping("/users")
	public ModelAndView listUsers() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", authFacade.getUser());
		mv.addObject("userList", userService.getAll());

		mv.setViewName("user/list");
		return mv;
	}

}
