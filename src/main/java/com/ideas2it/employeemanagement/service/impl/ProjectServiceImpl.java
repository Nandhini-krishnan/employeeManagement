package com.ideas2it.employeemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.TechStack;
import com.ideas2it.employeemanagement.repository.EmployeeRepository;
import com.ideas2it.employeemanagement.repository.ProjectRepository;
import com.ideas2it.employeemanagement.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	public Project insertProject(Project project) {
		return projectRepository.save(project);
	}

	public List<Project> getProjects() {
		return projectRepository.findAll();
	}

	public Project getProjectById(int id) {
		return projectRepository.findById(id).orElse(null);
	}

	public String deleteProjectById(int id) {
		projectRepository.deleteById(id);
		return "deleted successfully " + id;
	}

	public String updateProject(Project project) {
		String message = null;
		if (projectRepository.existsById(project.getId())) {
			Project existingProject = projectRepository.findById(project.getId()).orElse(null);
			existingProject.setName(project.getName());
			existingProject.setStartDate(project.getStartDate());
			existingProject.setEndDate(project.getEndDate());
			if (null != project.getTechStacks()) {
				List<TechStack> input = existingProject.getTechStacks();
				input.addAll(project.getTechStacks());
				existingProject.setTechStacks(input);
			}
			projectRepository.save(existingProject);
			projectRepository.save(project);	
		    message = project.getName() + " Update Successfully";
		} else {
			message = "Project not found";
		}
		return message;
	}

}
