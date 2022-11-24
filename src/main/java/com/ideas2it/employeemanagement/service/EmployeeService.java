package com.ideas2it.employeemanagement.service;

import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;

public interface EmployeeService {

	Employee insertEmployee(Employee employee);
	
    List<Employee> getEmployees();
	
    Employee getEmployeeById(int id);
    
	String deleteEmployeeById(int id);

	String updateEmployee(Employee employee);
}
