package com.ideas2it.employeemanagement.controller;

import java.util.List;
import java.util.Optional;

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
import com.ideas2it.employeemanagement.dto.TechStackDto;
import com.ideas2it.employeemanagement.service.TechStackService;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

/**
 * <p>
 * Gets input for the techStacks and then return the results of techStack's
 * operations.
 * </p>
 *
 * @author Naganandhini version 1.0 10-AUG-2022
 */
@RestController
@RequestMapping("/techStack")
public class TechStackController {
	
	@Autowired
	private TechStackService techStackService;
	
	/**
     * <p>
     * To Create the techStack.
     * </p>
     *
     * @param techStackDto - the techStack to be created
     * @return - the created techStack with HttpStatus.CREATED
     * @throws EmployeeManagementException - if the input is invalid
     */
	@PostMapping("/")
	public ResponseEntity<TechStackDto> insertTechStack(@RequestBody TechStackDto techStackDto) {
		return new ResponseEntity<>(techStackService.insertTechStack(techStackDto), HttpStatus.CREATED);		
	}
	
	/**
	 * <p>
	 * To display all the active techStacks stored in the techStacks table.
	 * </p>
	 * 
	 * @return - the list of active techStacks
	 * @throws EmployeeManagementException - when techStack table is empty
	 */
	@GetMapping("/")
	public ResponseEntity<List<TechStackDto>> getTechStacks() throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(techStackService.getTechStacks());		
	}
	
	/**
     * <p>
     * To get the techStack for the given techStack id.
     * </p>
     *
     * @param id - an techStack id for which the techStack to be returned
     * @return   - the techStack if the techStack id is found
     * @throws EmployeeManagementException - if the techStack id is not found 
     */
	@GetMapping("/{id}")
	public ResponseEntity<TechStackDto> getTechStackById(@RequestParam int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(techStackService.getTechStackById(id));		
	}
	
	/**
     * <p>
     * To remove the techStack for the given techStack id.
     * </p>
     *
     * @param id - an techStack id to be removed
     * @return   - the success message with HttpStatus.Ok
     * @throws EmployeeManagementException - if the techStack is not found
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTechStackById(@PathVariable int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.OK).body(techStackService.deleteTechStackById(id));
	}
	
	/**
     * <p>
     * To update the techStack for the given techStack id.
     * </p>
     *
     * @param  id - an techStack id to be updated
     * @return - the success message if it is updated
     * @throws EmployeeManagementException - if the techStack is not found, if the techStack is not updated
     */
	@PutMapping("/{id}")
	public ResponseEntity<String> updateProject(@RequestBody TechStackDto techStackDto, @PathVariable int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.OK).body(techStackService.updateTechStack(techStackDto, id)); 
	}
	
}
