package com.ideas2it.employeemanagement.service;

import java.util.Date;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDto;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

public interface EmployeeService {

	EmployeeDto insertEmployee(EmployeeDto employeeDto);
	
    List<EmployeeDto> getEmployees() throws EmployeeManagementException;
	
    EmployeeDto getEmployeeById(int id) throws EmployeeManagementException;
    
	String deleteEmployeeById(int id) throws EmployeeManagementException;

	String updateEmployee(EmployeeDto employeeDto, int id) throws EmployeeManagementException;
	
	List<EmployeeDto> getEmployeesInRange(Date startDate, Date endDate) throws EmployeeManagementException;
	
	List<EmployeeDto> getEmployeesByMultipleId(List<Integer> listOfId) throws EmployeeManagementException;
	
	List<EmployeeDto> searchEmployees(String keyword) throws EmployeeManagementException;
	
	
}
