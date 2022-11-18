
package com.ideas2it.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ideas2it.model.Employee;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.impl.EmployeeServiceImpl;
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
@RequestMapping("/employee")
public class EmployeeController {
	private EmployeeService employeeService = new EmployeeServiceImpl();

	@RequestMapping
	public String createEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "createEmployee";
	}

	@PostMapping({"/insert","/employee/insert"})
	public String insertEmployee(@RequestParam String dateBirth, @RequestParam String dateJoin,
			@ModelAttribute("employee") Employee employee, Model model, HttpServletRequest request) {
		try {
			String message;
			employee.setDateOfBirth(DateUtil.getParsedDate(dateBirth));
			employee.setDateOfJoin(DateUtil.getParsedDate(dateJoin));
			System.out.println(request.getAttribute("projects"));
			System.out.println(employee);
			if (0 != employee.getId()) {
				System.out.println(employee);
				message = employeeService.updateEmployee(employee) ? "Update Successfully" :  "Update UnSuccessfull";
				
			} else {
				model.addAttribute("employee", employeeService.createEmployee(employee));
				message = "Insert Successfully";
			}
			model.addAttribute("message", message);
		} catch (EmployeeManagementException e) {
			EmployeeManagementLogger.displayErrorLogs(e.getMessage());
		}
		return "redirect:/createEmployee";
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

	@PostMapping({ "/getEmployeeById", "/edit" })
	private String getEmployee(@RequestParam("id") int id, Model model, HttpServletRequest request) {
		String view = null;
		try {
			Employee employee = employeeService.getEmployeeById(id);
			model.addAttribute("employee", employee);
			request.setAttribute("projects", employee.getProjects());
			if (request.getServletPath().equals("/employee/edit")) {
				view = "createEmployee";
			} else {
				view = "getEmployee";
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
		return view;
	}

	@PostMapping("/remove")
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

}
