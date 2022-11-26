package com.ideas2it.employeemanagement.service;

import java.util.Date;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDto;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

public interface EmployeeService {

	EmployeeDto insertEmployee(EmployeeDto employeeDto);
	
    List<EmployeeDto> getEmployees() throws EmployeeManagementException;
	
    EmployeeDto getEmployeeById(int id);
    
	String deleteEmployeeById(int id);

	String updateEmployee(EmployeeDto employeeDto, int id);
	
	List<EmployeeDto> getEmployeesInRange(Date startDate, Date endDate);
	
	List<EmployeeDto> getEmployeesByMultipleId(List<Integer> listOfId);
	
	List<EmployeeDto> searchEmployees(String keyword);
	
	
}
