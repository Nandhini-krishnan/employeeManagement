package com.ideas2it.employeemanagement.util.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ideas2it.employeemanagement.dto.EmployeeDto;
import com.ideas2it.employeemanagement.dto.ProjectDto;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.Project;

/**
 * <p>
 * Handles the converting process from entity to DTO for employee.
 * </p
 *
 * @author Naganandhini
 * @version 1.0 25-NOV-2022
 */
public class EmployeeMapper {

	/**
     * <p>
     * To convert from entity to DTO for employee
     * </p>
     *
     * @param employee - an employee entity to be converted into DTO.
     * @return   - the employee DTO.
     */
	public static EmployeeDto convertIntoDto(Employee employee) {
		List<Project> projects;
		List<ProjectDto> projectsDto = null;		
		EmployeeDto employeeDto = null;
		if (null != employee) {
			employeeDto = new EmployeeDto();
			employeeDto.setName(employee.getName());
			employeeDto.setAddress(employee.getAddress());
			employeeDto.setEmployeeCode(employee.getEmployeeCode());
			employeeDto.setBloodGroup(employee.getBloodGroup());
			employeeDto.setPreviousOrganisationName(employee.getPreviousOrganisationName());
			employeeDto.setDateOfBirth(employee.getDateOfBirth());
			employeeDto.setDateOfJoin(employee.getDateOfJoin());
			projects = employee.getProjects();
			if (null != projects) {
				projectsDto = projects.stream().map((Function<Project, ProjectDto>) p -> {
					p.setEmployees(null);
					return ProjectMapper.convertIntoDto(p);
				}).collect(Collectors.toList());
			}
			employeeDto.setProjects(projectsDto);
		}
		return employeeDto;
	}
	
	/**
     * <p>
     * To convert from DTO to Entity for employee
     * </p>
     *
     * @param employee - an employee DTO to be converted into entity.
     * @return   - the employee.
     */

	public static Employee convertIntoEntity(EmployeeDto employeeDto) {
		List<ProjectDto> projectsDto;
		List<Project> projects = null;
		Employee employee = null;
		if (null != employeeDto) {
			employee = new Employee();
			employee.setName(employeeDto.getName());
			employee.setAddress(employeeDto.getAddress());
			employee.setEmployeeCode(employeeDto.getEmployeeCode());
			employee.setBloodGroup(employeeDto.getBloodGroup());
			employee.setPreviousOrganisationName(employeeDto.getPreviousOrganisationName());
			employee.setDateOfBirth(employeeDto.getDateOfBirth());
			employee.setDateOfJoin(employeeDto.getDateOfJoin());
			projectsDto = employeeDto.getProjects();
			if (null != projectsDto) {
				projects = projectsDto.stream().map((Function<ProjectDto, Project>) p -> {
					p.setEmployees(null);
					return ProjectMapper.convertIntoEntity(p);
				}).collect(Collectors.toList());
			}
			employee.setProjects(projects);
		}
		return employee;
	}

	/**
     * <p>
     * To convert from DTO to Entity for the list of employees
     * </p>
     *
     * @param employees - the list of employees DTO to be converted into entity.
     * @return   - the list of employees DTO.
     */
	public static List<EmployeeDto> convertIntoEmployeesDto(List<Employee> employees) {
		List<EmployeeDto> employeesDto = null;
		if (null != employees) {
			employeesDto = employees.stream().map(e -> convertIntoDto(e)).collect(Collectors.toList());
		}		
		return employeesDto;	
	}
}

