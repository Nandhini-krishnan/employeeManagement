package com.ideas2it.employeemanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ideas2it.employeemanagement.model.TechStack;
import com.ideas2it.employeemanagement.model.TechStackDto;
import com.ideas2it.employeemanagement.service.TechStackService;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

@RestController
@RequestMapping("/techStack")
public class TechStackController {
	
	@Autowired
	private TechStackService techStackService;
	
	@PostMapping("/insert")
	public TechStackDto insertTechStack(@RequestBody TechStackDto techStackDto) {
		return techStackService.insertTechStack(techStackDto);		
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<TechStackDto>> getTechStacks() throws EmployeeManagementException {
		return ResponseEntity.of(Optional.of(techStackService.getTechStacks()));		
	}
	
	@GetMapping("/getById")
	public TechStackDto getTechStackById(@RequestParam int id) {
		return techStackService.getTechStackById(id);		
	}
	
	@DeleteMapping("/remove/{id}")
	public String deleteTechStackById(@PathVariable int id) {
		return techStackService.deleteTechStackById(id);
	}
	
	@PutMapping("/update/{id}")
	public String updateProject(@RequestBody TechStackDto techStackDto, @PathVariable int id) {
		return techStackService.updateTechStack(techStackDto, id); 
	}
	
}
