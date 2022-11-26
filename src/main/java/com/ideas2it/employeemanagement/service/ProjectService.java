package com.ideas2it.employeemanagement.service;

import java.util.List;

import com.ideas2it.employeemanagement.model.ProjectDto;

public interface ProjectService {

	ProjectDto insertProject(ProjectDto projectDto);

	List<ProjectDto> getProjects();

	ProjectDto getProjectById(int id);

	String deleteProjectById(int id);

	String updateProject(ProjectDto projectDto, int id);
}
