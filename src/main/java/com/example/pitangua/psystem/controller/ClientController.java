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

import com.example.pitangua.psystem.domain.CepAddress;
import com.example.pitangua.psystem.domain.Client;
import com.example.pitangua.psystem.security.IAuthenticationFacade;
import com.example.pitangua.psystem.service.IClientService;
import com.example.pitangua.psystem.service.IUserService;
import com.example.pitangua.psystem.validation.CepAddressValidator;
import com.example.pitangua.psystem.validation.ClientValidator;

@Controller
public class ClientController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IClientService clientService;

	@Autowired
	private IAuthenticationFacade authFacade;

	@Autowired
	private ClientValidator clientValidator;

	@Autowired
	private CepAddressValidator cepAddressValidator;

	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();

		if (target == null) {
			return;
		}

		System.out.println("Target=" + target);

		if (target instanceof Client) {
			dataBinder.setValidator(clientValidator);
		} else if (target instanceof CepAddress) {
			dataBinder.setValidator(cepAddressValidator);
		}
	}

	@GetMapping("/clients/view/{id}")
	public ModelAndView viewClient(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("view-client");
		mv.addObject("id", id);

		mv.addObject("client", clientService.getById(id));
		mv.addObject("activeUser", authFacade.getUser().getName());
		mv.addObject("userEmail", authFacade.getUser().getEmail());
		return mv;
	}

	@GetMapping("/clients/registered")
	public ModelAndView registeredClients() {
		ModelAndView mv = new ModelAndView("registered-clients");

		mv.addObject("activeUser", authFacade.getUser().getName());
		mv.addObject("userEmail", authFacade.getUser().getEmail());
		mv.addObject("clientCount", clientService.getClientCount(authFacade.getUser().getId()));
		mv.addObject("clientList", clientService.getAll());
		return mv;
	}

	@GetMapping("/clients/register")
	public ModelAndView registerClient() {
		ModelAndView mv = new ModelAndView("register-client");
		Client clientForm = new Client();
		mv.addObject("clientForm", clientForm);

		mv.addObject("user", authFacade.getUser());
		return mv;
	}

	@PostMapping("/clients/register")
	public ModelAndView registerClientRequest(Model model, @ModelAttribute("clientForm") @Validated Client clientForm,
			BindingResult result, final RedirectAttributes redirectAttributes) throws SQLException {

		model.addAttribute("user", authFacade.getUser());
		model.addAttribute("userList", userService.getAll());

		if (!result.hasErrors()) {
			try {
				clientService.insert(clientForm);

				redirectAttributes.addFlashAttribute("registerSuccess", true);
				return new ModelAndView("register-client");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
			}
		}

		return new ModelAndView("register-client");
	}
}
