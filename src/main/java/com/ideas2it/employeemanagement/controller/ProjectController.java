package com.ideas2it.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.ProjectDto;
import com.ideas2it.employeemanagement.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping("/insert")
	public ProjectDto insertEmployee(@RequestBody ProjectDto projectDto) {
		return projectService.insertProject(projectDto);		
	}
	
	@GetMapping("/get")
	public List<ProjectDto> getProjects() {
		return projectService.getProjects();		
	}
	
	@GetMapping("/getById/{id}")
	public ProjectDto getProjectById(@PathVariable int id) {
		return projectService.getProjectById(id);		
	}
	
	@DeleteMapping("/remove/{id}")
	public String deleteProjectById(@PathVariable int id) {
		return projectService.deleteProjectById(id);
	}
	
	@PutMapping("/update/{id}")
	public String updateProject(@RequestBody ProjectDto projectDto, @PathVariable int id) {
		return projectService.updateProject(projectDto, id); 
	}
	

}
