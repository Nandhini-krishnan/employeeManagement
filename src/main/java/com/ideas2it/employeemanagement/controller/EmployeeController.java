package com.ideas2it.employeemanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.employeemanagement.dto.EmployeeDto;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.util.DateUtil;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

/**
 * <p>
 * Gets input for the employees and then return the results of employee's
 * operations.
 * </p>
 *
 * @author Naganandhini version 1.0 10-AUG-2022
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired 
	private EmployeeService employeeService;

	/**
     * <p>
     * To Create the employee.
     * </p>
     *
     * @param employeeDto - the employee to be created
     * @return - the created employee with HttpStatus.CREATED
     * @throws EmployeeManagementException - if the input is invalid
     */
	@PostMapping("/")
	public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto)
			throws EmployeeManagementException {
		return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
	}

	/**
	 * <p>
	 * To display all the active employees stored in the employees table.
	 * </p>
	 * 
	 * @return - the list of active employees
	 * @throws EmployeeManagementException - when employees table is empty
	 */
	@GetMapping("/")
	public ResponseEntity<List<EmployeeDto>> getEmployees() throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.getEmployees());
	}

	 /**
     * <p>
     * To get the employee for the given employee id.
     * </p>
     *
     * @param id - an employee id for which the employee to be returned
     * @return   - the employee if the employee id is found
     * @throws EmployeeManagementException - if the employee id is not found 
     */
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.getEmployeeById(id));
	}

	/**
     * <p>
     * To remove the employee for the given employee id.
     * </p>
     *
     * @param id - an employee id to be removed
     * @return   - the success message with HttpStatus.Ok
     * @throws EmployeeManagementException - if the employee is not found
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.deleteEmployeeById(id));
	}

	/**
     * <p>
     * To update the employee for the given employee id.
     * </p>
     *
     * @param  employeeId - an employee id to be updated
     * @return            - the success message if it is updated
     * @throws EmployeeManagementException - if the employee is not found, if the employee is not updated
     */
	@PutMapping("/{id}")
	public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDto employee, @PathVariable int id)
			throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(employee, id));
	}

	/**
     * <p>
     * To fetch the employees between the range of date.
     * </p>
     *
     * @param startDate - a start date for which employee will be filtered
     * @param endDate - an end date for which employee will be filtered
     * @return        - the list of filtered employees
     * @throws EmployeeManagementException - if there is no employee based on the given range
     */
	@GetMapping("/get-employees-in-range")
	public ResponseEntity<List<EmployeeDto>> getEmployeesInRange(@RequestParam String startDate, @RequestParam String endDate)
			throws EmployeeManagementException {
		List<EmployeeDto> employees = employeeService.getEmployeesInRange(DateUtil.getParsedDate(startDate), DateUtil.getParsedDate(endDate));
		return ResponseEntity.status(HttpStatus.FOUND).body(employees);
	}

	/**
     * <p>
     * To fetch the employees for the multiple given id.
     * </p>
     *
     * @param listOfId - the list of id for which the employee to be returned
     * @return   - the list of filtered employees
     * @throws EmployeeManagementException - if there is no employee for the given id
     */
	@GetMapping("/get-employees-by-multiple-id")
	public ResponseEntity<List<EmployeeDto>> getEmployeesByMultipleId(@RequestBody List<Integer> listOfId)
			throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.getEmployeesByMultipleId(listOfId));
	}

	/**
     * <p>
     * To search the employees.
     * </p>
     *
     * @param keyword - an input for which employee will be filtered
     * @return - the list of filtered employees
     * @throws EmployeeManagementException - if there is no employee based on the given condition
     */
	@GetMapping("/search")
	public ResponseEntity<List<EmployeeDto>> searchEmployees(@RequestParam String keyword) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.searchEmployees(keyword));
	}

}
