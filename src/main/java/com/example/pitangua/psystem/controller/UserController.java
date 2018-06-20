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
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/psychologists")
	public ModelAndView listPsychologists() {
		ModelAndView mv = new ModelAndView("registered-users");
		User user = authFacade.getUser();

		mv.addObject("user", user);
		mv.addObject("userCount", userService.getCountByClinic(user.getClinicId()));
		mv.addObject("userList", userService.getByClinic(user.getClinicId()));

		return mv;
	}

	@PostMapping("/psychologists/delete/{cpf}")
	public String deleteUser(@PathVariable String cpf, Model model, @ModelAttribute("user") @Validated User user,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		User activeUser = authFacade.getUser();
		model.addAttribute("cpf", cpf);

		try {
			userService.remove(user);
			model.addAttribute("activeUser", activeUser.getName());
			model.addAttribute("userEmail", activeUser.getEmail());
			model.addAttribute("userCount", userService.getCountByClinic(authFacade.getUser().getClinicId()));
			model.addAttribute("userList", userService.getByClinic(activeUser.getClinicId()));
			return "redirect:/psychologists";
		} catch (SQLException e) {
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
		}

		return "redirect:/psychologists";
	}

	@GetMapping("/psychologists/view/{cpf}")
	public ModelAndView viewUser(@PathVariable String cpf) {
		ModelAndView mv = new ModelAndView("view-user");
		mv.addObject("cpf", cpf);

		mv.addObject("user", userService.getByCpf(cpf));
		mv.addObject("activeUser", authFacade.getUser().getName());
		mv.addObject("userEmail", authFacade.getUser().getEmail());
		return mv;
	}

	@GetMapping("/psychologists/edit/{cpf}")
	public ModelAndView editUser(@PathVariable String cpf) {
		ModelAndView mv = new ModelAndView("edit-user");
		mv.addObject("cpf", cpf);

		mv.addObject("user", authFacade.getUser());
		mv.addObject("userForm", userService.getByCpf(cpf));
		return mv;
	}

	@PostMapping("/psychologists/edit/{cpf}")
	public ModelAndView editUserRequest(@PathVariable String cpf, Model model,
			@ModelAttribute("userForm") @Validated User userForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		model.addAttribute("cpf", cpf);
		model.addAttribute("user", authFacade.getUser());
		userForm.setCpf(cpf);

		if (!result.hasErrors()) {
			try {
				userService.update(userForm);
				model.addAttribute("userForm", userService.getByCpf(cpf));
				model.addAttribute("updateSuccess", true);
				return new ModelAndView("edit-user");
			} catch (SQLException e) {
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
			}
		}

		return new ModelAndView("edit-user");
	}
}
