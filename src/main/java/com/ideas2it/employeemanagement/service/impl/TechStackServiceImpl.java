package com.ideas2it.employeemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.TechStack;
import com.ideas2it.employeemanagement.repository.TechStackRepository;
import com.ideas2it.employeemanagement.service.TechStackService;

@Service
public class TechStackServiceImpl implements TechStackService {
	
	@Autowired
	private TechStackRepository techStackRepository;

	public TechStack insertTechStack(TechStack techStack) {
		return techStackRepository.save(techStack);
	}

	public List<TechStack> getTechStacks() {
		return techStackRepository.findAll();
	}

	public TechStack getTechStackById(int id) {
		return techStackRepository.findById(id).orElse(null);
	}

	public String deleteTechStackById(int id) {
		techStackRepository.deleteById(id);
		return "deleted successfully " + id;
	}

	public String updateTechStack(TechStack techStack) {
		String message = null;
		if (techStackRepository.existsById(techStack.getId())) {
			TechStack existingTechStack = techStackRepository.findById(techStack.getId()).orElse(null);
			existingTechStack.setName(techStack.getName());
			existingTechStack.setVersion(techStack.getVersion());
			if (null != techStack.getProjects()) {
				List<Project> input = existingTechStack.getProjects();
				input.addAll(techStack.getProjects());
				existingTechStack.setProjects(input);
			}
			techStackRepository.save(existingTechStack);	
		    message = techStack.getName() + " Update Successfully";
		} else {
			message = "TechStack not found";
		}
		return message;
	}

}

