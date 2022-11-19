package com.ideas2it.controller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideas2it.model.Project;
import com.ideas2it.model.TechStack;
import com.ideas2it.service.TechStackService;
import com.ideas2it.service.impl.TechStackServiceImpl;
import com.ideas2it.util.Constants;
import com.ideas2it.util.DateUtil;
import com.ideas2it.util.Validation;
import com.ideas2it.util.exception.EmployeeManagementException;
import com.ideas2it.util.logger.EmployeeManagementLogger;

@Controller
@RequestMapping
("/techStack")
public class TechStackController {
    private TechStackService techStackService = new TechStackServiceImpl();

    @RequestMapping
	public String createTechStack(Model model) {
		model.addAttribute("techStack", new TechStack());
		return "createTechStack";
	}

	@PostMapping({"/insertTechStack"})
	public String insertTechStack(@ModelAttribute("techStack") TechStack techStack, Model model, HttpServletRequest request) {
		try {
			String message;		
			if (0 != techStack.getId()) {
				message = techStackService.updateTechStack(techStack) ? "Update Successfully" :  "Update UnSuccessfull";
				
			} else {
				model.addAttribute("techStack", techStackService.createTechStack(techStack));
				message = "Insert Successfully";
			}
			model.addAttribute("message", message);
		} catch (EmployeeManagementException e) {
			EmployeeManagementLogger.displayErrorLogs(e.getMessage());
		}
		return "redirect:/createTechStack";
	}

	
	@GetMapping({"/getTechStacks", "/getExistingTechStacks"} )
	private String getTechStacks(Model model, HttpServletRequest request) {
		String view = null;
		try {
			List<TechStack> techStacks = techStackService.getTechStacks();	
			if (!techStacks.isEmpty()) {
				model.addAttribute("TechStacks", techStacks);
				if (request.getServletPath().equals("/getExistingTechStacks")) {
					view = "assignTechStacks";
				} else {
					view = "displayTechStacks";
				}
			} else {
				model.addAttribute("message", "No Record Found");
				view = "error";
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
		return view;
	}

	@PostMapping({ "/getTechStackById", "/edit" })
	private String getTechStack(@RequestParam("id") int id, Model model, HttpServletRequest request) {
		String view = null;
		try {
			TechStack techStack = techStackService.getTechStackById(id);
			model.addAttribute("techStack", techStack);
			//request.setAttribute("projects", project.getProjects());
			if (request.getServletPath().equals("/techStack/edit")) {
				view = "createTechStack";
			} else {
				view = "getTechStack";
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
		return view;
	}

	@PostMapping("/remove")
	private String removeTechStackById(@RequestParam int id, Model model) {
		try {
			if (techStackService.isIdExist(id)) {
				if (techStackService.removeTechStackById(id)) {
					model.addAttribute("message", "Deleted Successfully");
				} else {
					model.addAttribute("message", "Deletion Unsuccessfull");
				}
			} else {
				model.addAttribute("message", "techStack Not Found");
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
		return "removeTechStack";
	}
}