package com.ideas2it.employeemanagement.util.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ideas2it.employeemanagement.dto.EmployeeDto;
import com.ideas2it.employeemanagement.dto.ProjectDto;
import com.ideas2it.employeemanagement.dto.TechStackDto;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.TechStack;

/**
 * <p>
 * Handles the converting process from entity to DTO for project.
 * </p
 *
 * @author Naganandhini
 * @version 1.0 25-NOV-2022
 */
public class ProjectMapper {
	
	/**
     * <p>
     * To convert from entity to DTO for project
     * </p>
     *
     * @param project - an project entity to be converted into DTO.
     * @return   - the project DTO.
     */
	public static ProjectDto convertIntoDto(Project project) {
		List<Employee> employees;
		List<TechStack> techStacks;
		ProjectDto projectDto = null;
		List<EmployeeDto> employeesDto = null;
		List<TechStackDto> techStacksDto = null;
		if (null != project) {
			projectDto = new ProjectDto();
			projectDto.setName(project.getName());
			projectDto.setStartDate(project.getStartDate());
			projectDto.setEndDate(project.getEndDate());
			employees = project.getEmployees();
			if (null != employees) {
				employeesDto = employees.stream().map((Function<Employee, EmployeeDto>) e -> {
					e.setProjects(null);
					return EmployeeMapper.convertIntoDto(e);
				}).collect(Collectors.toList());
			}
			projectDto.setEmployees(employeesDto);
			techStacks = project.getTechStacks();
			if (null != techStacks) {
				techStacksDto = techStacks.stream().map((Function<TechStack, TechStackDto>) t -> {
					t.setProjects(null);
					return TechStackMapper.convertIntoDto(t);
				}).collect(Collectors.toList());
			}
			projectDto.setTechStacks(techStacksDto);
		}
		return projectDto;
	}
	
	/**
     * <p>
     * To convert from DTO to Entity for project
     * </p>
     *
     * @param project - an project DTO to be converted into entity.
     * @return   - the project.
     */
	public static Project convertIntoEntity(ProjectDto projectDto) {
		List<EmployeeDto> employeesDto;
		List<TechStackDto> techStacksDto;
		List<Employee> employees = null;
		List<TechStack> techStacks = null;
		Project project = null;
		if (null != projectDto) {
			project = new Project();
			project.setName(projectDto.getName());
			project.setStartDate(projectDto.getStartDate());
			project.setEndDate(projectDto.getEndDate());		
			employeesDto = projectDto.getEmployees();
			if (null != employeesDto) {
				employees = employeesDto.stream().map((Function<EmployeeDto, Employee>) e -> {
					e.setProjects(null);
					return EmployeeMapper.convertIntoEntity(e);
				}).collect(Collectors.toList());
			}
			project.setEmployees(employees);
			techStacksDto = projectDto.getTechStacks();
			if (null != techStacksDto) {
				techStacks = techStacksDto.stream().map((Function<TechStackDto, TechStack>) t -> {
					t.setProjects(null);
					return TechStackMapper.convertIntoEntity(t);
				}).collect(Collectors.toList());
			}
			project.setTechStacks(techStacks);
		}
		return project;
	}
	
	/**
     * <p>
     * To convert from DTO to Entity for the list of projects
     * </p>
     *
     * @param projects - the list of projects DTO to be converted into entity.
     * @return   - the list of projects DTO.
     */
	public static List<ProjectDto> convertIntoProjectsDto(List<Project> projects) {
		List<ProjectDto> projectsDto = null;
		if (null != projects) {
			projectsDto = projects.stream().map(p -> convertIntoDto(p)).collect(Collectors.toList());
		}		
		return projectsDto;	
	}
}
