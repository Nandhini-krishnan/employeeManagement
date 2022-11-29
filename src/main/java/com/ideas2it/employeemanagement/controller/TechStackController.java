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

import com.ideas2it.employeemanagement.model.ProjectDto;
import com.ideas2it.employeemanagement.model.TechStackDto;
import com.ideas2it.employeemanagement.service.TechStackService;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

@RestController
@RequestMapping("/techStack")
public class TechStackController {
	
	@Autowired
	private TechStackService techStackService;
	
	@PostMapping("/insert")
	public ResponseEntity<TechStackDto> insertTechStack(@RequestBody TechStackDto techStackDto) {
		return new ResponseEntity<>(techStackService.insertTechStack(techStackDto), HttpStatus.CREATED);		
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<TechStackDto>> getTechStacks() throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(techStackService.getTechStacks());		
	}
	
	@GetMapping("/getById")
	public ResponseEntity<TechStackDto> getTechStackById(@RequestParam int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.FOUND).body(techStackService.getTechStackById(id));		
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteTechStackById(@PathVariable int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.OK).body(techStackService.deleteTechStackById(id));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateProject(@RequestBody TechStackDto techStackDto, @PathVariable int id) throws EmployeeManagementException {
		return ResponseEntity.status(HttpStatus.OK).body(techStackService.updateTechStack(techStackDto, id)); 
	}
	
}
