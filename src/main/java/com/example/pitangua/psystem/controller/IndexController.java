package com.example.pitangua.psystem.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.pitangua.psystem.dao.ClientDAO;
import com.example.pitangua.psystem.dao.DocumentDAO;
import com.example.pitangua.psystem.dao.ScheduleAppointmentDAO;
import com.example.pitangua.psystem.dao.UserDAO;
import com.example.pitangua.psystem.domain.User;
import com.example.pitangua.psystem.security.IAuthenticationFacade;

@Controller
public class IndexController {
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private IAuthenticationFacade authFacade;

	@GetMapping("/")
	public String index() {
		return "redirect:/dashboard";
	}

	@GetMapping("/dashboard")
	public ModelAndView dashboard(HttpServletResponse http) {
		ModelAndView mv = new ModelAndView("index");
		displayActiveUser(mv);
		displayCount(mv);
		return mv;
	}

	@GetMapping("/403")
	public ModelAndView error() {
		ModelAndView mv = new ModelAndView();
		String errorMessage = "You are not authorized for the requested data.";
		mv.addObject("errorMsg", errorMessage);
		mv.setViewName("403");
		return mv;
	}

	@GetMapping("/admin")
	public String admin() {
		// Just for test purposes
		return "app-calendar";
	}

	public void displayActiveUser(ModelAndView mv) {
		User activeUser = authFacade.getUser();
		mv.addObject("user", activeUser);
	}

	public void displayCount(ModelAndView mv) {
		User activeUser = authFacade.getUser();
		ClientDAO clientDAO = new ClientDAO();
		ScheduleAppointmentDAO appointmentDAO = new ScheduleAppointmentDAO();
		DocumentDAO documentDAO = new DocumentDAO();
		HashMap<String, String> countMap = new HashMap<>();

		countMap.put("psychologist",
				userDAO.getPsychologistCount(activeUser.getClinicId()) == 1 ? "Psychologist" : "Psychologists");
		countMap.put("client", clientDAO.getClientCount(activeUser.getId()) == 1 ? "Client" : "Clients");
		countMap.put("appointment",
				appointmentDAO.getAppointmentCount(activeUser.getId()) == 1 ? "Appointment" : "Appointments");
		countMap.put("document", documentDAO.getDocumentCount(activeUser.getId()) == 1 ? "Document" : "Documents");

		mv.addObject("psychologistCount", userDAO.getPsychologistCount(activeUser.getClinicId()));
		mv.addObject("clientCount", clientDAO.getClientCount(activeUser.getId()));
		mv.addObject("appointmentCount", appointmentDAO.getAppointmentCount(activeUser.getId()));
		mv.addObject("documentCount", documentDAO.getDocumentCount(activeUser.getId()));

		mv.addAllObjects(countMap);
	}

}
