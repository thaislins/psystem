package com.example.pitangua.psystem.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.pitangua.psystem.domain.Anamnesis;
import com.example.pitangua.psystem.domain.Client;
import com.example.pitangua.psystem.domain.User;
import com.example.pitangua.psystem.security.IAuthenticationFacade;
import com.example.pitangua.psystem.service.IAnamnesisService;
import com.example.pitangua.psystem.service.IClientService;
import com.example.pitangua.psystem.validation.AnamnesisValidator;

@Controller
public class AnamnesisController {

	@Autowired
	private AnamnesisValidator anamnesisValidator;

	@Autowired
	private IAnamnesisService anamnesisService;

	@Autowired
	private IClientService clientService;

	@Autowired
	private IAuthenticationFacade authFacade;

	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();

		if (target == null) {
			return;
		}

		System.out.println("Target=" + target);

		if (target instanceof Anamnesis) {
			dataBinder.setValidator(anamnesisValidator);
		}
	}

	@GetMapping("/anamnesis/view/{id}")
	public ModelAndView viewAnamnesis(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("anamnesis/view");
		mv.addObject("id", id);

		Anamnesis anamnesisForm = anamnesisService.getByClientId(id);
		if (anamnesisForm == null) {
			anamnesisForm = new Anamnesis();
			anamnesisForm.setClientId(id);
		}

		mv.addObject("client", clientService.getById(id));
		mv.addObject("anamnesis", anamnesisForm);
		mv.addObject("user", authFacade.getUser());
		return mv;
	}

	@GetMapping("/anamnesis/register/{id}")
	public ModelAndView registerAnamnesis(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("anamnesis/register");
		mv.addObject("id", id);

		Anamnesis anamnesisForm = anamnesisService.getByClientId(id);
		if (anamnesisForm == null) {
			anamnesisForm = new Anamnesis();
			anamnesisForm.setClientId(id);
		}

		mv.addObject("client", clientService.getById(id));
		mv.addObject("anamnesisForm", anamnesisForm);
		mv.addObject("user", authFacade.getUser());
		return mv;
	}

	@PostMapping("/anamnesis/register/{id}")
	public String registerAnamnesisRequest(@PathVariable int id, Model model,
			@ModelAttribute("anamnesisForm") Anamnesis anamnesisForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		User user = authFacade.getUser();
		Client client = clientService.getById(id);

		if (client == null || client.getPsychologistId() != user.getId()) {
			return "redirect:/clients/registered";
		}

		model.addAttribute("id", id);
		model.addAttribute("user", user);
		model.addAttribute("client", client);
		anamnesisForm.setClientId(id);

		if (!result.hasErrors()) {
			try {
				anamnesisService.insert(anamnesisForm);
				model.addAttribute("anamnesisForm", anamnesisService.getByClientId(id));
				model.addAttribute("registerSuccess", true);
			} catch (SQLException e) {
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
			}
		}

		return "anamnesis/register";
	}

}
