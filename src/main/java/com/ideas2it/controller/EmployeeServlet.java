package com.ideas2it.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Project;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.ProjectService;
import com.ideas2it.service.impl.EmployeeServiceImpl;
import com.ideas2it.service.impl.ProjectServiceImpl;
import com.ideas2it.util.Constants;
import com.ideas2it.util.DateUtil;
import com.ideas2it.util.enumeration.BloodGroup;
import com.ideas2it.util.exception.EmployeeManagementException;
import com.ideas2it.util.logger.EmployeeManagementLogger;

@WebServlet(urlPatterns = { "/insertEmployee", "/getEmployees", "/getEmployeeById", "/removeEmployeeById",
		"/getEmployeeForUpdate", "/updateEmployee", "/getEmployeesByExperience", "/getEmployeesInRange",
		"/searchEmployees", "/getEmployeesByMultipleId", "/assignProjects", "/getProjectsForAssign"})
public class EmployeeServlet extends HttpServlet {
	EmployeeService employeeService = new EmployeeServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/insertEmployee":
			createEmployee(request, response);
			break;
		case "/assignProjects":
			assignProjects(request, response);
			break;
		case "/getEmployees":
			showEmployees(request, response);
			break;
		case "/getEmployeeById":
			getEmployeeById(request, response);
			break;
		case "/removeEmployeeById":
			removeEmployee(request, response);
			break;
		case "/getEmployeeForUpdate":
			getEmployeeById(request, response);
			break;
		case "/updateEmployee":
			updateEmployee(request, response);
			break;
		case "/getEmployeesByExperience":
			getEmployeesByExperience(request, response);
			break;
		case "/getEmployeesInRange":
			getEmployeesInRange(request, response);
			break;
		case "/searchEmployees":
			searchEmployees(request, response);
			break;
		case "/getEmployeesByMultipleId":
			getEmployeesByMultipleId(request, response);
			break;
		case "/getProjectsForAssign":
			getProjectsForAssign(request, response);
			break;
		}

	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String action = request.getServletPath();
//		switch (action) {
//
//		}
//	}

	/**
	 * <p>
	 * To create the employee by getting the employee details from user and display
	 * employee name which is created.
	 * </p>
	 */
	private void createEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Employee employee = employeeService.createEmployee(request.getParameter("name"),
					request.getParameter("address"), BloodGroup.valueOf(request.getParameter("bloodGroup")),
					DateUtil.getParsedDate(request.getParameter("dateOfBirth")),
					Boolean.valueOf(request.getParameter("experience")),
					DateUtil.getParsedDate(request.getParameter("dateOfJoin")),
					request.getParameter("previousOrganisationName"));
			HttpSession session = request.getSession();
			session.setAttribute("Employee", employee);
			response.sendRedirect("insertEmployee.jsp");
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	/**
	 * <p>
	 * To display all the employees stored in the employees table. if the employees
	 * table is empty, display no record found.
	 * </p>
	 */
	private void showEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			List<Employee> employees = employeeService.getEmployees();
			HttpSession session = request.getSession();
			if (!employees.isEmpty()) {
				session.setAttribute("Employees", employees);
			} else {
				session.setAttribute("Employees", "No Record Found");
			}
			response.sendRedirect("displayEmployees.jsp");
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	/**
	 * <p>
	 * Get the employee id from the user to search the employee. If the employee is
	 * found then display the employee, otherwise employee not found.
	 * </p>
	 */
	private void getEmployeeById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Employee employee = employeeService.getEmployeeById(Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			session.setAttribute("Employee", employee);
			/*
			 * if (null != employee) { session.setAttribute("Employee", employee); if
			 * (request.getServletPath().equals("/getEmployeeForUpdate")) {
			 * System.out.println(employee); response.sendRedirect("updateEmployee.jsp"); }
			 * else { response.sendRedirect("getEmployee.jsp"); } } else {
			 * session.setAttribute("Employee", Constants.EMPLOYEE_NOT_FOUND); }
			 */
			System.out.println(employee);
			response.sendRedirect("getEmployee.jsp");
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	/**
	 * <p>
	 * Get the employee id from the user on which employee need to be removed. if
	 * the employee removed successfully then display employee is removed, otherwise
	 * employee not found.
	 * </p>
	 */
	private void removeEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			boolean isDeleted = employeeService.removeEmployeeById(Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			session.setAttribute("isDeleted", isDeleted);
			response.sendRedirect("removeEmployee.jsp");
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
	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Employee employee = employeeService.getEmployeeById(Integer.parseInt(request.getParameter("id")));
			employee.setName(request.getParameter("name"));
			employee.setAddress(request.getParameter("address"));
			employee.setBloodGroup(BloodGroup.valueOf(request.getParameter("bloodGroup")));
			employee.setDateOfBirth(DateUtil.getParsedDate(request.getParameter("dateOfBirth")));
			employee.setDateOfJoin(DateUtil.getParsedDate(request.getParameter("dateOfJoin")));
			employee.setExperience(Boolean.valueOf(request.getParameter("experience")));
			employee.setPreviousOrganisationName(request.getParameter("previousOrganisationName"));
			boolean isUpdated = employeeService.updateEmployee(employee);
			HttpSession session = request.getSession();
			session.setAttribute("isUpdated", isUpdated);
			response.sendRedirect("updateEmployee.jsp");
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	/**
	 * <p>
	 * To get the employees by experience and display the employees.
	 * </p>
	 */
	private void getEmployeesByExperience(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			int yearsOfExperience = Integer.parseInt(request.getParameter("yearsOfExperience"));
			List<Employee> employees = employeeService.getEmployeesByExperience(yearsOfExperience);
			HttpSession session = request.getSession();
			if (!employees.isEmpty()) {
				session.setAttribute("Employees", employees);
			} else {
				session.setAttribute("Employees", Constants.NO_RECORD_FOUND);
			}
			response.sendRedirect("displayEmployees.jsp");
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	/**
	 * <p>
	 * To get the employees by the range of date of join and display the employees.
	 * </p>
	 */
	private void getEmployeesInRange(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			List<Employee> employees = employeeService.getEmployeesInRange(
					DateUtil.getParsedDate(request.getParameter("startDate")),
					DateUtil.getParsedDate(request.getParameter("endDate")));
			HttpSession session = request.getSession();
			if (!employees.isEmpty()) {
				session.setAttribute("Employees", employees);
			} else {
				session.setAttribute("Employees", Constants.NO_RECORD_FOUND);
			}
			response.sendRedirect("displayEmployees.jsp");
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	/**
	 * <p>
	 * To search the employees by name and display the employees.
	 * </p>
	 */
	private void searchEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			List<Employee> employees = employeeService.searchEmployees(request.getParameter("value"));
			HttpSession session = request.getSession();
			if (!employees.isEmpty()) {
				session.setAttribute("Employees", employees);
			} else {
				session.setAttribute("Employees", Constants.NO_RECORD_FOUND);
			}
			response.sendRedirect("displayEmployees.jsp");
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	/**
	 * <p>
	 * To get the employees by multiple id and display the employees.
	 * </p>
	 */
	private void getEmployeesByMultipleId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession();
			int size = (int) session.getAttribute("size");
			List<Integer> listOfId = new ArrayList<>();
			for (int index = 0; index < size; index++) {
				listOfId.add(Integer.parseInt(request.getParameter("" + (index + 1))));
			}
			List<Employee> employees = employeeService.getEmployeesByMultipleId(listOfId);
			if (!employees.isEmpty()) {
				session.setAttribute("Employees", employees);
			} else {
				session.setAttribute("Employees", Constants.NO_RECORD_FOUND);
			}
			response.sendRedirect("displayEmployees.jsp");
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	/**
	 * <p>
	 * To assign the projects for employee.
	 * </p>
	 */
	private void assignProjects(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession();
			int employeeId = Integer.parseInt(request.getParameter("id"));
			boolean isPresent = employeeService.isIdExist(employeeId);
			session.setAttribute("employeeId", employeeId);
			if (isPresent) {
				session.setAttribute("isPresent", isPresent);
				response.sendRedirect("assignProjects.jsp");
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
	private void getProjectsForAssign(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession();
		    ProjectService projectService = new ProjectServiceImpl();
		    int employeeId = (int) session.getAttribute("employeeId");
		    List<Project> projects = new ArrayList<>();
		    String[] projectsToAssign = request.getParameterValues("project");
		    for (int index = 0; index < projectsToAssign.length ; index++) {
			    projects.add(projectService.getProjectById(Integer.parseInt(projectsToAssign[index])));
		    }
		    session.setAttribute("Employee",employeeService.assignProjects(employeeId, projects));
	    } catch (EmployeeManagementException employeeManagementException) {
		    EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    }
	}
}
