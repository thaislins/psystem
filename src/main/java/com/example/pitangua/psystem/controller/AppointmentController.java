package com.example.pitangua.psystem.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.pitangua.psystem.domain.ScheduleAppointment;
import com.example.pitangua.psystem.domain.User;
import com.example.pitangua.psystem.security.IAuthenticationFacade;
import com.example.pitangua.psystem.service.IAppointmentService;
import com.example.pitangua.psystem.service.IClientService;

@Controller
public class AppointmentController {

	@Autowired
	private IClientService clientService;

	@Autowired
	private IAppointmentService appointmentService;

	@Autowired
	private IAuthenticationFacade authFacade;

	@GetMapping("/appointments/schedule")
	public ModelAndView scheduleAppointment() {
		ModelAndView mv = new ModelAndView("schedule-appointment");
		ScheduleAppointment appointmentForm = new ScheduleAppointment();

		mv.addObject("user", authFacade.getUser());
		mv.addObject("clientList", clientService.getAll(authFacade.getUser().getId()));
		mv.addObject("appointmentForm", appointmentForm);
		return mv;
	}

	@PostMapping("/appointments/schedule")
	public ModelAndView scheduleAppointmentRequest(Model model,
			@ModelAttribute("appointmentForm") @Validated ScheduleAppointment appointmentForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("schedule-appointment");

		model.addAttribute("user", authFacade.getUser());
		model.addAttribute("clientList", clientService.getAll(authFacade.getUser().getId()));
		appointmentForm.setPsychologistId(authFacade.getUser().getId());

		if (!result.hasErrors()) {
			try {
				appointmentService.insert(appointmentForm);
				redirectAttributes.addFlashAttribute("registerSuccess", true);
				model.addAttribute("registerSuccess", true);
				return new ModelAndView("schedule-appointment");
			} catch (SQLException e) {
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
			}
		}

		return new ModelAndView("schedule-appointment");
	}

	@GetMapping("/appointments")
	public ModelAndView appointments() {
		ModelAndView mv = new ModelAndView("appointments");
		User user = authFacade.getUser();

		mv.addObject("user", user);
		mv.addObject("appointmentCount", appointmentService.getAppointmentCount(user.getId()));
		mv.addObject("clientList", clientService.getClientsWithAppointments(user.getId()));
		return mv;
	}

	@GetMapping("/appointments/client/{id}")
	public ModelAndView clientAppointments(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("client-appointments");
		User user = authFacade.getUser();

		mv.addObject("id", id);
		mv.addObject("user", user);
		mv.addObject("client", clientService.getById(id));
		mv.addObject("appointmentList", appointmentService.getClientAppointments(id));
		return mv;
	}

}
