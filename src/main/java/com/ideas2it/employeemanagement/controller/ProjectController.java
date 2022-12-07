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

import com.ideas2it.employeemanagement.dto.ProjectDto;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.service.ProjectService;
import com.ideas2it.employeemanagement.util.DateUtil;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

/**
 * <p>
 * Gets input for the projects and then return the results of project's
 * operations.
 * </p>
 *
 * @author Naganandhini version 1.0 10-AUG-2022
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	/**
     * <p>
     * To Create the project.
     * </p>
     *
     * @param projectDto - the project to be created
     * @return - the created project with HttpStatus.CREATED
     * @throws EmployeeManagementException - if the input is invalid
     */
	@PostMapping("/")
	public ResponseEntity<ProjectDto> insertProject(@RequestBody ProjectDto projectDto)
			throws EmployeeManagementException {
		return new ResponseEntity<>(projectService.createProject(projectDto), HttpStatus.CREATED);
	}

	/**
	 * <p>
	 * To display all the active projects stored in the projects table.
	 * </p>
	 * 
	 * @return - the list of active projects
	 * @throws EmployeeManagementException - when projects table is empty
	 */
	@GetMapping("/")
	public ResponseEntity<List<ProjectDto>> getProjects() throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(projectService.getProjects());
	}

	/**
     * <p>
     * To get the project for the given project id.
     * </p>
     *
     * @param id - an project id for which the project to be returned
     * @return   - the project if the project id is found
     * @throws EmployeeManagementException - if the project id is not found 
     */
	@GetMapping("/{id}")
	public ResponseEntity<ProjectDto> getProjectById(@PathVariable int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(projectService.getProjectById(id));
	}

	/**
     * <p>
     * To remove the project for the given project id.
     * </p>
     *
     * @param id - an project id to be removed
     * @return   - the success message with HttpStatus.Ok
     * @throws EmployeeManagementException - if the project is not found
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProjectById(@PathVariable int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.deleteProjectById(id));
	}

	/**
     * <p>
     * To update the project for the given project id.
     * </p>
     *
     * @param  id - an project id to be updated
     * @return - the success message if it is updated
     * @throws EmployeeManagementException - if the project is not found, if the project is not updated
     */
	@PutMapping("/{id}")
	public ResponseEntity<String> updateProject(@RequestBody ProjectDto projectDto, @PathVariable int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.updateProject(projectDto, id));
	}

}
