package com.ideas2it.employeemanagement.service;

import java.util.List;

import com.ideas2it.employeemanagement.model.Project;

public interface ProjectService {
		
	    Project insertProject(Project project);
		
	    List<Project> getProjects();
		
	    Project getProjectById(int id);
	    
		String deleteProjectById(int id);

		String updateProject(Project project);

	}

