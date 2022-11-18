package com.ideas2it.controller;

import java.util.ArrayList;
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

import com.ideas2it.model.Employee;
import com.ideas2it.model.Project;
import com.ideas2it.model.TechStack;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.ProjectService;
import com.ideas2it.service.TechStackService;
import com.ideas2it.service.impl.EmployeeServiceImpl;
import com.ideas2it.service.impl.ProjectServiceImpl;
import com.ideas2it.service.impl.TechStackServiceImpl;
import com.ideas2it.util.DateUtil;
import com.ideas2it.util.exception.EmployeeManagementException;
import com.ideas2it.util.logger.EmployeeManagementLogger;

/**
 * <p>
 * Gets input for the projects and
 * then displays the results of project's operations.
 * </p>
 *
 * @author Naganandhini
 * version 1.0  19-SEP-2022
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
	private ProjectService projectService = new ProjectServiceImpl();

	@RequestMapping
	public String createProject(Model model) {
		model.addAttribute("project", new Project());
		return "createProject";
	}

	@PostMapping({"/insert","/project/insert"})
	public String insertProject(@RequestParam String startDate, @RequestParam String endDate,
			@ModelAttribute("project") Project project, Model model, HttpServletRequest request) {
		try {
			String message;
			project.setStartDate(DateUtil.getParsedDate(startDate));
			project.setEndDate(DateUtil.getParsedDate(endDate));			
			if (0 != project.getId()) {
				message = projectService.updateProject(project) ? "Update Successfully" :  "Update UnSuccessfull";
				
			} else {
				model.addAttribute("project", projectService.createProject(project));
				message = "Insert Successfully";
			}
			model.addAttribute("message", message);
		} catch (EmployeeManagementException e) {
			EmployeeManagementLogger.displayErrorLogs(e.getMessage());
		}
		return "redirect:/createProject";
	}

	
	@GetMapping("/getProjects")
	private String showProjects(Model model) {

		try {
			List<Project> projects = projectService.getProjects();
			model.addAttribute("projects", projects);
			if (!projects.isEmpty()) {
				model.addAttribute("message", projects);
			} else {
				model.addAttribute("message", "No Record Found");
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}

		return "displayProjects";
	}

	@PostMapping({ "/getProjectById", "/edit" })
	private String getProject(@RequestParam("id") int id, Model model, HttpServletRequest request) {
		String view = null;
		try {
			Project project = projectService.getProjectById(id);
			model.addAttribute("project", project);
			//request.setAttribute("projects", project.getProjects());
			if (request.getServletPath().equals("/project/edit")) {
				view = "createProject";
			} else {
				view = "getProject";
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
		return view;
	}

	@PostMapping("/remove")
	private String deleteProjectById(@RequestParam int id, Model model) {
		try {
			if (projectService.isIdExist(id)) {
				if (projectService.deleteProjectById(id)) {
					model.addAttribute("message", "Deleted Successfully");
				} else {
					model.addAttribute("message", "Deletion Unsuccessfull");
				}
			} else {
				model.addAttribute("message", "Project Not Found");
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
		return "removeProject";
	}

}
