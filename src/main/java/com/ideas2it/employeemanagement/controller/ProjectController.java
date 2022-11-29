package com.ideas2it.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.ideas2it.employeemanagement.util.DateUtil;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@PostMapping("/insert")
	public ResponseEntity<ProjectDto> insertEmployee(@RequestBody ProjectDto projectDto)
			throws EmployeeManagementException {
		ProjectDto createdProject = null;
		if (DateUtil.compareTwoDates(projectDto.getStartDate(), projectDto.getEndDate())) {
			createdProject = projectService.insertProject(projectDto);
		}
		return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
	}

	@GetMapping("/get")
	public ResponseEntity<List<ProjectDto>> getProjects() throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(projectService.getProjects());
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<ProjectDto> getProjectById(@PathVariable int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(projectService.getProjectById(id));
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteProjectById(@PathVariable int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.deleteProjectById(id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateProject(@RequestBody ProjectDto projectDto, @PathVariable int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.updateProject(projectDto, id));
	}

}
