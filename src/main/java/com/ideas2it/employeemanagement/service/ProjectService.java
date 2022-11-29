package com.ideas2it.employeemanagement.service;

import java.util.List;

import com.ideas2it.employeemanagement.model.ProjectDto;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

public interface ProjectService {

	ProjectDto insertProject(ProjectDto projectDto);

	List<ProjectDto> getProjects() throws EmployeeManagementException;

	ProjectDto getProjectById(int id) throws EmployeeManagementException;

	String deleteProjectById(int id) throws EmployeeManagementException;

	String updateProject(ProjectDto projectDto, int id) throws EmployeeManagementException;
}
