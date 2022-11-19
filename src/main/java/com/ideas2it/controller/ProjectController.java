package com.ideas2it.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
 * Gets input for the projects and then displays the results of project's
 * operations.
 * </p>
 *
 * @author Naganandhini version 1.0 19-SEP-2022
 */
@Controller
public class ProjectController {
	private ProjectService projectService = new ProjectServiceImpl();

	@RequestMapping("/project")
	public String createProject(Model model) {
		model.addAttribute("project", new Project());
		return "createProject";
	}

	@PostMapping({ "/insertProject" })
	public String insertProject(@RequestParam String startDate, @RequestParam String endDate,
			@ModelAttribute("project") Project project, Model model, HttpServletRequest request) {
		try {
			String message;
			project.setStartDate(DateUtil.getParsedDate(startDate));
			project.setEndDate(DateUtil.getParsedDate(endDate));
			project.setTechStacks((List<TechStack>)request.getSession().getAttribute("techStacks"));
			if (0 != project.getId()) {
				message = projectService.updateProject(project) ? "Update Successfully" : "Update UnSuccessfull";

			} else {
				model.addAttribute("project", projectService.createProject(project));
				message = "Insert Successfully";
			}
			model.addAttribute("message", message);
		} catch (EmployeeManagementException e) {
			EmployeeManagementLogger.displayErrorLogs(e.getMessage());
		}
		return "createProject";
	}

	@PostMapping({"getProjects", "/getExistingProjects"} )
	private String getProjects(Model model, HttpServletRequest request) {
		String view = null;
		try {
			List<Project> projects = projectService.getProjects();	
			if (!projects.isEmpty()) {
				model.addAttribute("Projects", projects);
				if (request.getServletPath().equals("/getExistingProjects")) {
					view = "assignProjects";
				} else {
					view = "displayProjects";
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

	@PostMapping({"/getProjectById", "/editProject"})
	private String getProject(@RequestParam("id") int id, Model model, HttpServletRequest request) {
		String view = null;
		try {
			Project project = projectService.getProjectById(id);
			model.addAttribute("project", project);
            request.getSession().setAttribute("projects", project.getTechStacks());
			if (request.getServletPath().equals("/editProject")) {
				view = "createProject";
			} else {
				view = "getProject";
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
		return view;
	}

	@PostMapping("/removeProject")
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
	
	/**
	 * <p>
	 * To assign the Tech Stacks for project.
	 * </p>
	 */
	@PostMapping("/assignTechStacks")
	private String assignTechStacks(@RequestParam int id, Model model, HttpServletRequest request) {
		String view = null;
		try {
			boolean isPresent = projectService.isIdExist(id);
			request.getSession().setAttribute("employeeId", id);
			if (isPresent) {
				model.addAttribute("isPresent", isPresent);
				view = "assignTechStacks";
			} else {
				model.addAttribute("message", "Project not found");
				view = "error";
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
		return view;
	}

	/**
     * <p>
     * To get the Tech Stacks which will be assigned for project
     * </p>
     *
     * @return - the jsp page to show the message
     */
	@PostMapping("/getTechStacksForAssign")
	private String getProjectsForAssign(Model model, HttpServletRequest request) throws IOException {
		try {
			TechStackService techStackService = new TechStackServiceImpl();
		    int employeeId = (int) request.getSession().getAttribute("employeeId");
		    List<TechStack> techStacks = new ArrayList<>();
			String[] techStacksToAssign = request.getParameterValues("techStack");
			for (int index = 0; index < techStacksToAssign.length; index++) {
				techStacks.add(techStackService.getTechStackById(Integer.parseInt(techStacksToAssign[index])));
			}
			request.setAttribute("Project", projectService.assignTechStacks(employeeId, techStacks));
	    } catch (EmployeeManagementException employeeManagementException) {
		    EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    }
		return "assignTechStacks";
	}

}
