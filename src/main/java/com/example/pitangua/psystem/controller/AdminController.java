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

import com.example.pitangua.psystem.domain.CepAddress;
import com.example.pitangua.psystem.domain.Clinic;
import com.example.pitangua.psystem.domain.User;
import com.example.pitangua.psystem.security.IAuthenticationFacade;
import com.example.pitangua.psystem.service.IClinicService;
import com.example.pitangua.psystem.validation.CepAddressValidator;
import com.example.pitangua.psystem.validation.ClinicValidator;

@Controller
public class AdminController {

	@Autowired
	private IClinicService clinicService;

	@Autowired
	private IAuthenticationFacade authFacade;

	@Autowired
	private ClinicValidator clinicValidator;

	@Autowired
	private CepAddressValidator cepAddressValidator;

	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();

		if (target == null) {
			return;
		}

		System.out.println("Target=" + target);

		if (target instanceof Clinic) {
			dataBinder.setValidator(clinicValidator);
		} else if (target instanceof CepAddress) {
			dataBinder.setValidator(cepAddressValidator);
		}
	}

	@GetMapping("/admin/clinic")
	public ModelAndView editClinic() {
		ModelAndView mv = new ModelAndView("admin/edit-clinic");

		User user = authFacade.getUser();
		Clinic clinicForm = clinicService.getById(user.getClinicId());

		System.out.println("id: " + clinicForm.getId());

		mv.addObject("user", user);
		mv.addObject("clinicForm", clinicForm);

		return mv;
	}

	@PostMapping("/admin/clinic")
	public String editClinicRequest(Model model, @ModelAttribute("clinicForm") @Validated Clinic clinicForm,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		System.out.println("id: " + clinicForm.getId());
		if (!result.hasErrors()) {
			try {
				clinicService.update(clinicForm);
			} catch (SQLException e) {
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
			}
		}

		model.addAttribute("user", authFacade.getUser());

		return "admin/edit-clinic";
	}

}
