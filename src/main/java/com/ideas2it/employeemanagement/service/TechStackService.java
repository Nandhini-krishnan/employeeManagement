package com.ideas2it.employeemanagement.service;

import java.util.List;

import com.ideas2it.employeemanagement.model.TechStack;

public interface TechStackService {

	TechStack insertTechStack(TechStack techStack);

	List<TechStack> getTechStacks();

	TechStack getTechStackById(int id);

	String deleteTechStackById(int id);

	String updateTechStack(TechStack techStack);
}
