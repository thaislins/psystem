package com.example.pitangua.psystem.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.pitangua.psystem.dao.ClientDAO;
import com.example.pitangua.psystem.dao.UserDAO;
import com.example.pitangua.psystem.domain.User;
import com.example.pitangua.psystem.security.IAuthenticationFacade;

@Controller
public class IndexController {

	private UserDAO userDAO;
	private ClientDAO clientDAO;
	
	@Autowired
	private IAuthenticationFacade authFacade;
	
	public IndexController() {
		userDAO = new UserDAO();
	}
	
	@GetMapping("/")
	public ModelAndView index(HttpServletResponse http) {
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
		UserDetails userDetails = authFacade.getUserDetails();
		User user = userDAO.getByEmail(userDetails.getUsername());
		
		mv.addObject("activeUser", user.getName());
		mv.addObject("userEmail", user.getEmail());
	}
	
	public void displayCount(ModelAndView mv) {
		ClientDAO clientDAO = new ClientDAO();
		HashMap<String,String> countMap = new HashMap<String,String>();
		String[] countNames = new String[4];
		
		countNames[0] = userDAO.getPsychologistCount() == 1 ? "Psychologist" : "Psychologists";
		countNames[1] = clientDAO.getClientCount() == 1 ? "Clients" : "Clients";
		//countNames[2] = userDao.getPsychologistCount() == 1 ? "Appointment" : "Appointments";
		//countNames[3] = userDao.getPsychologistCount() == 1 ? "Document" : "Documents";

		countMap.put("psychologist", countNames[0]);
		countMap.put("client", countNames[1]);
		
		mv.addObject("psychologistCount", userDAO.getPsychologistCount());
		mv.addObject("clientCount", clientDAO.getClientCount());	
		mv.addAllObjects(countMap);
	}
}
