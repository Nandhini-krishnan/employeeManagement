package com.ideas2it.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Project;
import com.ideas2it.model.TechStack;
import com.ideas2it.service.ProjectService;
import com.ideas2it.service.TechStackService;
import com.ideas2it.service.impl.ProjectServiceImpl;
import com.ideas2it.service.impl.TechStackServiceImpl;
import com.ideas2it.util.DateUtil;
import com.ideas2it.util.enumeration.BloodGroup;
import com.ideas2it.util.exception.EmployeeManagementException;
import com.ideas2it.util.logger.EmployeeManagementLogger;

@WebServlet(urlPatterns = { "/insertProject", "/getProjects", "/getProjectById", "/removeProjectById",
		"/getProjectForUpdate", "/updateProject", "/getExistingProjects", "/getTechStacksForAssign", "/assignTechStacks" })
public class ProjectServlet extends HttpServlet {
	ProjectService projectService = new ProjectServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/insertProject":
			createProject(request, response);
			break;
		case "/assignTechStacks":
			assignTechStacks(request, response);
			break;
		case "/getProjects":
			getProjects(request, response);
			break;
		case "/getProjectById":
			getProjectById(request, response);
			break;
		case "/removeProjectById":
			removeProjectById(request, response);
			break;
		case "/updateProject":
			updateProject(request, response);
			break;
		case "/getExistingProjects":
			getProjects(request, response);
			break;
		case "/getTechStacksForAssign":
			getTechStacksForAssign(request, response);
			break;
		}
	}

	private void createProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Project project = projectService.createProject(request.getParameter("name"),
					DateUtil.getParsedDate(request.getParameter("startDate")),
					DateUtil.getParsedDate(request.getParameter("endDate")));
			HttpSession session = request.getSession();
			session.setAttribute("Project", project);
			response.sendRedirect("insertProject.jsp");
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	private void getProjects(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			List<Project> projects = projectService.getProjects();
			System.out.println(projects);
			HttpSession session = request.getSession();
			session.setAttribute("Projects", projects);
			if (request.getServletPath().equals("/getExistingProjects")) {
				System.out.println("jsp assign called from getprojects");
				response.sendRedirect("assignProjects.jsp");
			} else {
				response.sendRedirect("displayProjects.jsp");
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	private void getProjectById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Project project = projectService.getProjectById(Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			session.setAttribute("Project", project);
			if (request.getServletPath().equals("/getProjectForUpdate")) {
				response.sendRedirect("updateProject.jsp");
			} else {
				response.sendRedirect("getProject.jsp");
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	private void removeProjectById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			boolean isDeleted = projectService.deleteProjectById(Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			session.setAttribute("isDeleted", isDeleted);
			response.sendRedirect("removeProject.jsp");
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}
	
	/**
	 * <p>
	 * Get the employee id from the user on which employee need to be updated. If
	 * the employee id exist then update the employee, otherwise display employee
	 * not found.
	 * </p>
	 */
	private void updateProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Project project = projectService.getProjectById(Integer.parseInt(request.getParameter("id")));
			project.setName(request.getParameter("name"));
			project.setStartDate(DateUtil.getParsedDate(request.getParameter("startDate")));
			project.setEndDate(DateUtil.getParsedDate(request.getParameter("endDate")));
			boolean isUpdated = projectService.updateProject(project);
			HttpSession session = request.getSession();
			session.setAttribute("isUpdated", isUpdated);
			response.sendRedirect("updateEmployee.jsp");
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}


	/**
	 * <p>
	 * To assign the projects for employee.
	 * </p>
	 */
	private void assignTechStacks(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession();
			int projectId = Integer.parseInt(request.getParameter("id"));
			boolean isPresent = projectService.isIdExist(projectId);
			session.setAttribute("projectId", projectId);
			if (isPresent) {
				session.setAttribute("isPresent", isPresent);
				response.sendRedirect("assignTechStacks.jsp");
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	/**
	 * <p>
	 * To get the projects which will be assigned for employee
	 * </p>
	 *
	 * @return - the list of projects
	 */
	private void getTechStacksForAssign(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession();
			TechStackService techStackService = new TechStackServiceImpl();
			int employeeId = (int) session.getAttribute("projectId");
			List<TechStack> techStacks = new ArrayList<>();
			String[] techStacksToAssign = request.getParameterValues("techStack");
			for (int index = 0; index < techStacksToAssign.length; index++) {
				techStacks.add(techStackService.getTechStackById(Integer.parseInt(techStacksToAssign[index])));
			}
			session.setAttribute("Project", projectService.assignTechStacks(employeeId, techStacks));
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

}
