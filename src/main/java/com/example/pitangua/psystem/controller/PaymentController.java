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
	public ModelAndView payments() {
		ModelAndView mv = new ModelAndView("payments/list");
		User user = authFacade.getUser();

		mv.addObject("user", user);
		mv.addObject("paymentsCount", paymentService.getPaymentsCount(user.getId()));
		mv.addObject("clientList", clientService.getClientsWithPayments(user.getId()));
		return mv;
	}

	@GetMapping("/payments/client/{id}")
	public ModelAndView clientPayments(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("payments/client");
		User user = authFacade.getUser();

		mv.addObject("id", id);
		mv.addObject("user", user);
		mv.addObject("client", clientService.getById(id));
		mv.addObject("paymentsList", paymentService.getClientPayments(id));
		return mv;
	}

	@GetMapping("/payments/register")
	public ModelAndView registerPayment() {
		ModelAndView mv = new ModelAndView("payments/register");
		Payment paymentForm = new Payment();
		User user = authFacade.getUser();

		mv.addObject("user", user);
		mv.addObject("clientList", clientService.getAll(user.getId()));
		mv.addObject("paymentForm", paymentForm);
		return mv;
	}

	@PostMapping("/payments/register")
	public String registerPaymentRequest(Model model,
			@ModelAttribute("paymentForm") @Validated Payment paymentForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		User user = authFacade.getUser();

		model.addAttribute("user", user);
		model.addAttribute("clientList", clientService.getAll(user.getId()));
		paymentForm.setPsychologistId(user.getId());
		System.out.println(paymentForm);

		if (!result.hasErrors()) {
			try {
				paymentService.insert(paymentForm);
				redirectAttributes.addFlashAttribute("registerSuccess", true);
				return "redirect:/payments/register";
			} catch (SQLException e) {
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
			}
		}

		return "payments/register";
	}

	@PostMapping("/payments/delete/{id}")
	public String deletePayment(@PathVariable int id, Model model,
			@ModelAttribute("payment") @Validated Payment payment,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		try {
			paymentService.remove(payment);
		} catch (SQLException e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
		}

		return "redirect:/payments";
	}
}
