package com.ideas2it.employeemanagement.controller;

import java.util.List;
import java.util.Optional;

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
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/insert")
	public ResponseEntity<EmployeeDto> insertEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto createdEmployeeDto = employeeService.insertEmployee(employeeDto);
		if(null != createdEmployeeDto) {
			return ResponseEntity.of(Optional.of(employeeDto));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/get")
	public List<EmployeeDto> getEmployees() throws EmployeeManagementException {
		return employeeService.getEmployees();		
	}
	
	@GetMapping("/getById/{id}")
	public EmployeeDto getEmployeeById(@PathVariable int id) {
		return employeeService.getEmployeeById(id);		
	}
	
	@DeleteMapping("/remove/{id}")
	public String deleteEmployeeById(@PathVariable int id) {
		return employeeService.deleteEmployeeById(id);
	}
	
	@PutMapping("/update/{id}")
	public String updateEmployee(@RequestBody EmployeeDto employee, @PathVariable int id) {
		return employeeService.updateEmployee(employee, id); 
	}
	
	@GetMapping("/getEmployeesInRange/{startDate}/{endDate}")
	public List<EmployeeDto> getEmployeesInRange(@PathVariable String startDate, @PathVariable String endDate) {
		List<EmployeeDto> employees = null;
		try {
			employees = employeeService.getEmployeesInRange(DateUtil.getParsedDate(startDate), DateUtil.getParsedDate(endDate));
		} catch (EmployeeManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employees;
	}
	
	@GetMapping("/getEmployeesByMultipleId")
	public List<EmployeeDto> getEmployeesByMultipleId(@RequestBody List<Integer> listOfId) {
		return employeeService.getEmployeesByMultipleId(listOfId);
	}
	
	@GetMapping("/search/{keyword}")
	public List<EmployeeDto> searchEmployees(@PathVariable String keyword) {
		return employeeService.searchEmployees(keyword);
	}
	
}
