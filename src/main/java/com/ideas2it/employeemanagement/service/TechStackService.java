package com.ideas2it.employeemanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ideas2it.employeemanagement.model.TechStack;
import com.ideas2it.employeemanagement.model.TechStackDto;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

public interface TechStackService {

	TechStackDto insertTechStack(TechStackDto techStackDto);

	List<TechStackDto> getTechStacks() throws EmployeeManagementException;

	TechStackDto getTechStackById(int id) throws EmployeeManagementException;

	String deleteTechStackById(int id) throws EmployeeManagementException;

	String updateTechStack(TechStackDto techStackDto, int id) throws EmployeeManagementException;
}
