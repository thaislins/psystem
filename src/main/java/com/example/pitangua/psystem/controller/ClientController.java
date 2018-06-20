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
import com.example.pitangua.psystem.domain.User;
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
				model.addAttribute("registerSuccess", true);
				return new ModelAndView("register-client");
			} catch (SQLException e) {
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
			}
		}

		return new ModelAndView("register-client");
	}

	@GetMapping("/clients")
	public ModelAndView registeredClients() {
		ModelAndView mv = new ModelAndView("registered-clients");
		User user = authFacade.getUser();

		mv.addObject("activeUser", user.getName());
		mv.addObject("userEmail", user.getEmail());
		mv.addObject("clientCount", clientService.getClientCount(user.getId()));
		mv.addObject("clientList", clientService.getAll(user.getId()));
		return mv;
	}

	@PostMapping("/clients/delete/{id}")
	public String deleteClient(@PathVariable int id, Model model, @ModelAttribute("client") @Validated Client client,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		User user = authFacade.getUser();
		model.addAttribute("id", id);

		try {
			clientService.remove(client);
			model.addAttribute("activeUser", user.getName());
			model.addAttribute("userEmail", user.getEmail());
			model.addAttribute("clientCount", clientService.getClientCount(user.getId()));
			model.addAttribute("clientList", clientService.getAll(user.getId()));
			return "redirect:/clients/registered";
		} catch (SQLException e) {
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
		}

		return "redirect:/clients/registered";
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

	@GetMapping("/clients/edit/{id}")
	public ModelAndView editClient(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("edit-client");
		mv.addObject("id", id);

		mv.addObject("user", authFacade.getUser());
		mv.addObject("clientForm", clientService.getById(id));
		return mv;
	}

	@PostMapping("/clients/edit/{id}")
	public ModelAndView editClientRequest(@PathVariable int id, Model model,
			@ModelAttribute("clientForm") @Validated Client clientForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		model.addAttribute("id", id);
		model.addAttribute("user", authFacade.getUser());
		clientForm.setId(id);

		if (!result.hasErrors()) {
			try {
				clientService.update(clientForm);
				model.addAttribute("clientForm", clientService.getById(id));
				model.addAttribute("updateSuccess", true);
				return new ModelAndView("edit-client");
			} catch (SQLException e) {
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
			}
		}

		return new ModelAndView("edit-client");
	}

}
