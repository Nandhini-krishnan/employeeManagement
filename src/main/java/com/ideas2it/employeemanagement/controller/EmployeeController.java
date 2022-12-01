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
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.employeemanagement.model.EmployeeDto;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.util.DateUtil;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@GetMapping("/welcome")
	public String welcome() {
		return "WELCOME..!";
	}

	@Autowired 
	private EmployeeService employeeService;

	@PostMapping("/insert")
	public ResponseEntity<EmployeeDto> insertEmployee(@Valid @RequestBody EmployeeDto employeeDto)
			throws EmployeeManagementException {
		EmployeeDto createdEmployee = null;
		if (DateUtil.isValidAge(employeeDto.getDateOfBirth())
				&& DateUtil.compareTwoDates(employeeDto.getDateOfJoin(), employeeDto.getDateOfBirth())) {
			createdEmployee = employeeService.insertEmployee(employeeDto);
		}
		return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	}

	@GetMapping("/get")
	public ResponseEntity<List<EmployeeDto>> getEmployees() throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.getEmployees());
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.getEmployeeById(id));
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.deleteEmployeeById(id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDto employee, @PathVariable int id)
			throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(employee, id));
	}

	@GetMapping("/getEmployeesInRange/{startDate}/{endDate}")
	public ResponseEntity<List<EmployeeDto>> getEmployeesInRange(@PathVariable String startDate, @PathVariable String endDate)
			throws EmployeeManagementException {
		List<EmployeeDto> employees = employeeService.getEmployeesInRange(DateUtil.getParsedDate(startDate), DateUtil.getParsedDate(endDate));
		return ResponseEntity.status(HttpStatus.FOUND).body(employees);
	}

	@GetMapping("/getEmployeesByMultipleId")
	public ResponseEntity<List<EmployeeDto>> getEmployeesByMultipleId(@RequestBody List<Integer> listOfId)
			throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.getEmployeesByMultipleId(listOfId));
	}

	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<EmployeeDto>> searchEmployees(@PathVariable String keyword) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.searchEmployees(keyword));
	}

}
