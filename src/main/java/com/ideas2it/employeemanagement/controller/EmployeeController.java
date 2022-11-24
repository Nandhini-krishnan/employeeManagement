package com.ideas2it.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/insert")
	public Employee insertEmployee(@RequestBody Employee employee) {
		System.out.println(employee);
		return employeeService.insertEmployee(employee);		
	}
	
	@GetMapping("/get")
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();		
	}
	
	@GetMapping("/getById/{id}")
	public Employee getEmployeeById(@RequestParam int id) {
		return employeeService.getEmployeeById(id);		
	}
	
	@DeleteMapping("/remove/{id}")
	public String deleteEmployeeById(@PathVariable int id) {
		return employeeService.deleteEmployeeById(id);
	}
	
	@PutMapping("/update")
	public String updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee); 
	}
	
}
