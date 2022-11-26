package com.ideas2it.employeemanagement.util.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDto;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.ProjectDto;
import com.ideas2it.employeemanagement.model.TechStack;
import com.ideas2it.employeemanagement.model.TechStackDto;

public class Mapper {

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
					return convertIntoDto(p);
				}).collect(Collectors.toList());
			}
			employeeDto.setProjects(projectsDto);
		}
		return employeeDto;
	}

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
					return convertIntoDto(e);
				}).collect(Collectors.toList());
			}
			projectDto.setEmployees(employeesDto);
			techStacks = project.getTechStacks();
			if (null != techStacks) {
				techStacksDto = techStacks.stream().map((Function<TechStack, TechStackDto>) t -> {
					t.setProjects(null);
					return convertIntoDto(t);
				}).collect(Collectors.toList());
			}
			projectDto.setTechStacks(techStacksDto);
		}
		return projectDto;
	}
	
	public static TechStackDto convertIntoDto(TechStack techStack) {
		List<Project> projects;
		TechStackDto techStackDto = null;
		List<ProjectDto> projectsDto = null;
		if (null != techStack) {
			techStackDto = new TechStackDto();
			techStackDto.setName(techStack.getName());
			techStackDto.setVersion(techStack.getVersion());
			projects = techStack.getProjects();
			if (null != projects) {
				projectsDto = projects.stream().map((Function<Project, ProjectDto>) p -> {
					p.setTechStacks(null);
					return convertIntoDto(p);
				}).collect(Collectors.toList());
			}
			techStackDto.setProjects(projectsDto);
		}
		return techStackDto;
	}

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
					return convertIntoEntity(p);
				}).collect(Collectors.toList());
			}
			employee.setProjects(projects);
		}
		return employee;
	}

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
					return convertIntoEntity(e);
				}).collect(Collectors.toList());
			}
			project.setEmployees(employees);
			techStacksDto = projectDto.getTechStacks();
			if (null != techStacksDto) {
				techStacks = techStacksDto.stream().map((Function<TechStackDto, TechStack>) t -> {
					t.setProjects(null);
					return convertIntoEntity(t);
				}).collect(Collectors.toList());
			}
			project.setTechStacks(techStacks);
		}
		return project;
	}
	
	public static TechStack convertIntoEntity(TechStackDto techStackDto) {
		List<ProjectDto> projectsDto;
		List<Project> projects = null;
		TechStack techStack = null;
		if (null != techStackDto) {
			techStack = new TechStack();
			techStack.setName(techStackDto.getName());
			techStack.setVersion(techStackDto.getVersion());
			projectsDto = techStackDto.getProjects();
			if (null != projectsDto) {
				projects = projectsDto.stream().map((Function<ProjectDto, Project>) p -> {
						p.setTechStacks(null);
						return convertIntoEntity(p);
				}).collect(Collectors.toList());
			}
			techStack.setProjects(projects);
		}
		return techStack;
	}
	
	public static List<EmployeeDto> convertIntoEmployeesDto(List<Employee> employees) {
		List<EmployeeDto> employeesDto = null;
		if (null != employees) {
			employeesDto = employees.stream().map(e -> convertIntoDto(e)).collect(Collectors.toList());
		}		
		return employeesDto;	
	}
	
	public static List<ProjectDto> convertIntoProjectsDto(List<Project> projects) {
		List<ProjectDto> projectsDto = null;
		if (null != projects) {
			projectsDto = projects.stream().map(p -> convertIntoDto(p)).collect(Collectors.toList());
		}		
		return projectsDto;	
	}
	
	public static List<TechStackDto> convertIntoTechStacksDto(List<TechStack> techStacks) {
		List<TechStackDto> techStacksDto = null;
		if (null != techStacks) {
			techStacksDto = techStacks.stream().map(t -> convertIntoDto(t)).collect(Collectors.toList());
		}		
		return techStacksDto;	
	}

}
