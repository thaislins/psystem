package com.example.pitangua.psystem.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(HttpServletResponse http) {
		return "index";
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

}
