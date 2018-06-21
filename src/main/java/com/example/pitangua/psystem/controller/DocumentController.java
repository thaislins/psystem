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

import com.example.pitangua.psystem.domain.Document;
import com.example.pitangua.psystem.domain.User;
import com.example.pitangua.psystem.security.IAuthenticationFacade;
import com.example.pitangua.psystem.service.IClientService;
import com.example.pitangua.psystem.service.IDocumentService;
import com.example.pitangua.psystem.validation.DocumentValidator;

@Controller
public class DocumentController {

	@Autowired
	private IAuthenticationFacade authFacade;

	@Autowired
	private IClientService clientService;

	@Autowired
	private IDocumentService documentService;

	@Autowired
	private DocumentValidator documentValidator;

	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();

		if (target == null) {
			return;
		}

		System.out.println("Target=" + target);

		if (target instanceof Document) {
			dataBinder.setValidator(documentValidator);
		}
	}

	@GetMapping("/documents/issue")
	public ModelAndView issueDocument() {
		ModelAndView mv = new ModelAndView("documents/issue");
		Document documentForm = new Document();
		User user = authFacade.getUser();

		mv.addObject("clientList", clientService.getAll(user.getId()));
		mv.addObject("user", user);
		mv.addObject("documentForm", documentForm);
		return mv;
	}

	@PostMapping("/documents/issue")
	public ModelAndView issueDocumentRequest(Model model,
			@ModelAttribute("documentForm") @Validated Document documentForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		User user = authFacade.getUser();
		model.addAttribute("user", authFacade.getUser());
		documentForm.setPsychologistId(authFacade.getUser().getId());

		if (!result.hasErrors()) {
			try {
				documentService.insert(documentForm);
				redirectAttributes.addFlashAttribute("registerSuccess", true);
				model.addAttribute("registerSuccess", true);
				model.addAttribute("clientList", clientService.getAll(user.getId()));
				return new ModelAndView("documents/issue");
			} catch (SQLException e) {
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
			}
		}

		return new ModelAndView("documents/issue");

	}

	@GetMapping("/documents")
	public ModelAndView payments() {
		ModelAndView mv = new ModelAndView("documents/list");
		User user = authFacade.getUser();

		mv.addObject("user", user);
		mv.addObject("documentCount", documentService.getDocumentCount(user.getId()));
		mv.addObject("clientList", clientService.getClientsWithDocuments(user.getId()));
		return mv;
	}

	@GetMapping("/documents/client/{id}")
	public ModelAndView clientPayments(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("documents/client");
		User user = authFacade.getUser();

		mv.addObject("id", id);
		mv.addObject("user", user);
		mv.addObject("client", clientService.getById(id));
		mv.addObject("documentsList", documentService.getClientDocuments(id));
		return mv;
	}
}
