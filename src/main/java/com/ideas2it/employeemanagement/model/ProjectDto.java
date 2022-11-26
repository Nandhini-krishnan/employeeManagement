package com.ideas2it.employeemanagement.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

/**
 * <p>
 * Project class has the getters and setters for project details.
 * </p>
 *
 * @author Naganandhini
 * @version 1.0 09-AUG-2022
 */

@Component
public class ProjectDto {

	private String name;
	private Date startDate;
	private Date endDate;
	private List<EmployeeDto> employees;
	private List<TechStackDto> techStacks;

	public ProjectDto() {
	}

	public ProjectDto(String name, Date startDate, Date endDate) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}

	public List<EmployeeDto> getEmployees() {
		return employees;
	}

	public void setTechStacks(List<TechStackDto> techStacks) {
		this.techStacks = techStacks;
	}

	public List<TechStackDto> getTechStacks() {
		return techStacks;
	}

	/**
	 * <p>
	 * To display the project.
	 * </p>
	 */
	@Override
	public String toString() {
		StringBuilder project = new StringBuilder();
		project.append("\nProject name: ");
		project.append(name);
		project.append("\nProject Start Date : ");
		project.append(startDate);
		project.append("\nProject End Date: ");
		project.append(endDate);
		project.append("\nEmployees: ");
		if (null != employees && !employees.isEmpty()) {
			project.append(employees.stream().map(employee -> employee.getName()).collect(Collectors.joining(",")));
		} else {
			project.append(" No employees assigned");
		}
		project.append("\nTech Stacks: ");
		if (null != techStacks && !techStacks.isEmpty()) {
			project.append(techStacks.stream().map(employee -> employee.getName()).collect(Collectors.joining(",")));
		} else {
			project.append(" No tech stacks assigned");
		}
		return project.toString();
	}
}