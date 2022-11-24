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

import com.ideas2it.employeemanagement.model.TechStack;
import com.ideas2it.employeemanagement.service.TechStackService;

@RestController
@RequestMapping("/techStack")
public class TechStackController {
	
	@Autowired
	private TechStackService techStackService;
	
	@PostMapping("/insert")
	public TechStack insertTechStack(@RequestBody TechStack techStack) {
		return techStackService.insertTechStack(techStack);		
	}
	
	@GetMapping("/get")
	public List<TechStack> getTechStacks() {
		return techStackService.getTechStacks();		
	}
	
	@GetMapping("/getById")
	public TechStack getTechStackById(@RequestParam int id) {
		return techStackService.getTechStackById(id);		
	}
	
	@DeleteMapping("/remove/{id}")
	public String deleteTechStackById(@PathVariable int id) {
		return techStackService.deleteTechStackById(id);
	}
	
	@PutMapping("/update")
	public String updateProject(@RequestBody TechStack techStack) {
		return techStackService.updateTechStack(techStack); 
	}
	
}
