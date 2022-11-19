
package com.ideas2it.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.ProjectService;
import com.ideas2it.service.impl.EmployeeServiceImpl;
import com.ideas2it.service.impl.ProjectServiceImpl;
import com.ideas2it.util.Constants;
import com.ideas2it.util.DateUtil;
import com.ideas2it.util.exception.EmployeeManagementException;
import com.ideas2it.util.logger.EmployeeManagementLogger;

/**
 * <p>
 * Gets input for the employees and then displays the results of employee's
 * operations.
 * </p>
 *
 * @author Naganandhini version 1.0 10-AUG-2022
 */

@Controller
public class EmployeeController {
	private EmployeeService employeeService = new EmployeeServiceImpl();

	@RequestMapping("/employee")
	public String createEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "createEmployee";
	}

	@PostMapping({"/insertEmployee"})
	
	public String insertEmployee(@ModelAttribute("employee") Employee employee, @RequestParam String dateBirth, @RequestParam String dateJoin, Model model, HttpServletRequest request) {
		try {
			String message;
			employee.setDateOfBirth(DateUtil.getParsedDate(dateBirth));
			employee.setDateOfJoin(DateUtil.getParsedDate(dateJoin));
			employee.setProjects((List<Project>)request.getSession().getAttribute("projects"));
			if (0 != employee.getId()) {
				message = employeeService.updateEmployee(employee) ? "Update Successfully" :  "Update UnSuccessfull";
				
			} else {
				model.addAttribute("employee", employeeService.createEmployee(employee));
				message = "Insert Successfully";
			}
			model.addAttribute("message", message);
		} catch (EmployeeManagementException e) {
			EmployeeManagementLogger.displayErrorLogs(e.getMessage());
		}
		return "createEmployee";
	}

	/**
	 * <p>
	 * To display all the employees stored in the employees table. if the employees
	 * table is empty, display no record found.
	 * </p>
	 */
	@GetMapping("/getEmployees")
	private String showEmployees(Model model) {

		try {
			List<Employee> employees = employeeService.getEmployees();
			model.addAttribute("employees", employees);
			if (!employees.isEmpty()) {
				model.addAttribute("message", employees);
			} else {
				model.addAttribute("message", "No Record Found");
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}

		return "displayEmployees";
	}

	@PostMapping({ "/getEmployeeById", "/editEmployee" })
	private String getEmployee(@RequestParam("id") int id, Model model, HttpServletRequest request) {
		String view = null;
		try {
			Employee employee = employeeService.getEmployeeById(id);
			model.addAttribute("employee", employee);
			request.getSession().setAttribute("projects", employee.getProjects());
			if (request.getServletPath().equals("/editEmployee")) {
				view = "createEmployee";
			} else {
				view = "getEmployee";
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
		return view;
	}

	@PostMapping("/removeEmployee")
	private String deleteEmployeeById(@RequestParam int id, Model model) {
		try {
			if (employeeService.isIdExist(id)) {
				if (employeeService.removeEmployeeById(id)) {
					model.addAttribute("message", "Deleted Successfully");
				} else {
					model.addAttribute("message", "Deletion Unsuccessfull");
				}
			} else {
				model.addAttribute("message", "Employee Not Found");
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
		return "removeEmployee";
	}
	
	/**
	 * <p>
	 * To assign the projects for employee.
	 * </p>
	 */
	@PostMapping("/assignProjects")
	private String assignProjects(@RequestParam int id, Model model, HttpServletRequest request) {
		String view = null;
		try {
			HttpSession session = request.getSession();
			boolean isPresent = employeeService.isIdExist(id);
			session.setAttribute("employeeId", id);
			if (isPresent) {
				model.addAttribute("isPresent", isPresent);
				view = "assignProjects";
			} else {
				model.addAttribute("message", "Employee not found");
				view = "error";
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
		return view;
	}

	/**
     * <p>
     * To get the projects which will be assigned for employee
     * </p>
     *
     * @return - the list of projects
     */
	@PostMapping("/getProjectsForAssign")
	private String getProjectsForAssign(Model model, HttpServletRequest request) {
		try {
		    ProjectService projectService = new ProjectServiceImpl();
		    int employeeId = (int) request.getSession().getAttribute("employeeId");
		    List<Project> projects = new ArrayList<>();
		    String[] projectsToAssign = request.getParameterValues("project");
		    for (int index = 0; index < projectsToAssign.length ; index++) {
			    projects.add(projectService.getProjectById(Integer.parseInt(projectsToAssign[index])));
		    }
		    request.setAttribute("employee",employeeService.assignProjects(employeeId, projects));
	    } catch (EmployeeManagementException employeeManagementException) {
		    EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    }
		return "assignProjects";
	}

	/**
	 * <p>
	 * To get the employees by experience and display the employees.
	 * </p>
	 */
	@GetMapping("/getEmployeesByExperience")
	private String getEmployeesByExperience(@RequestParam int yearsOfExperience, Model model) {
		String view = null;
		try {
			List<Employee> employees = employeeService.getEmployeesByExperience(yearsOfExperience);
			System.out.println(employees);
			if (!employees.isEmpty()) {
				model.addAttribute("employees", employees);
				view = "displayEmployees";
			} else {
				model.addAttribute("error", Constants.NO_RECORD_FOUND);
				view = "error";
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			return "error";
		}
		return view;
	}

	/**
	 * <p>
	 * To get the employees by the range of date of join and display the employees.
	 * </p>
	 */
	@GetMapping("/getEmployeesInRange")
	private String getEmployeesInRange(@RequestParam String startDate, @RequestParam String endDate, Model model) {
		String view = null;
		try {
			List<Employee> employees = employeeService.getEmployeesInRange(
					DateUtil.getParsedDate(startDate),
					DateUtil.getParsedDate(endDate));
			if (!employees.isEmpty()) {
				model.addAttribute("employees", employees);
				view = "displayEmployees";
			} else {
				model.addAttribute("error", Constants.NO_RECORD_FOUND);
				view = "error";
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
		return view;
	}

	/**
	 * <p>
	 * To search the employees by name and display the employees.
	 * </p>
	 */
	@GetMapping("/search")
	private String searchEmployees(@RequestParam String value, Model model) {
		String view = null;
		try {
			List<Employee> employees = employeeService.searchEmployees(value);
			if (!employees.isEmpty()) {
				model.addAttribute("employees", employees);
				view = "displayEmployees";
			} else {
				model.addAttribute("error", Constants.NO_RECORD_FOUND);
				view = "error";
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
		return view;
	}

	/**
	 * <p>
	 * To get the employees by multiple id and display the employees.
	 * </p>
	 */
	@GetMapping("/getEmployeesByMultipleId")
	private String getEmployeesByMultipleId(HttpServletRequest request, Model model) {
		String view = null;
		try {
			int size = (int) request.getSession().getAttribute("size");
			List<Integer> listOfId = new ArrayList<>();
			for (int index = 0; index < size; index++) {
				listOfId.add(Integer.parseInt(request.getParameter("" + (index + 1))));
			}
			List<Employee> employees = employeeService.getEmployeesByMultipleId(listOfId);
			if (!employees.isEmpty()) {
				model.addAttribute("employees", employees);
				view = "displayEmployees";
			} else {
				model.addAttribute("error", Constants.NO_RECORD_FOUND);
				view = "error";
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
		return view;
	}
}
