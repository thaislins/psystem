package com.example.pitangua.psystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.pitangua.psystem.domain.Payment;
import com.example.pitangua.psystem.domain.User;
import com.example.pitangua.psystem.security.IAuthenticationFacade;
import com.example.pitangua.psystem.service.IClientService;
import com.example.pitangua.psystem.service.IPaymentService;
import com.example.pitangua.psystem.validation.PaymentValidator;

@Controller
public class PaymentController {
	@Autowired
	private PaymentValidator paymentValidator;

	@Autowired
	private IPaymentService paymentService;

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

		if (target instanceof Payment) {
			dataBinder.setValidator(paymentValidator);
		}
	}

	@GetMapping("/payments")
	public ModelAndView appointments() {
		ModelAndView mv = new ModelAndView("payments/list");
		User user = authFacade.getUser();

		mv.addObject("user", user);
		mv.addObject("paymentsCount", paymentService.getPaymentsCount(user.getId()));
		mv.addObject("clientList", clientService.getClientsWithPayments(user.getId()));
		return mv;
	}

	@GetMapping("/payments/client/{id}")
	public ModelAndView clientAppointments(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("payments/client");
		User user = authFacade.getUser();

		mv.addObject("id", id);
		mv.addObject("user", user);
		mv.addObject("client", clientService.getById(id));
		mv.addObject("paymentsList", paymentService.getClientPayments(id));
		return mv;
	}
}
