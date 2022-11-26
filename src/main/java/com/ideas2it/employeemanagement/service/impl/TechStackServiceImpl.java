package com.ideas2it.employeemanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.TechStack;
import com.ideas2it.employeemanagement.model.TechStackDto;
import com.ideas2it.employeemanagement.repository.TechStackRepository;
import com.ideas2it.employeemanagement.service.TechStackService;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.util.mapper.Mapper;

@Service
public class TechStackServiceImpl implements TechStackService {
	
	@Autowired
	private TechStackRepository techStackRepository;

	public TechStackDto insertTechStack(TechStackDto techStackDto) {
		return Mapper.convertIntoDto(techStackRepository.save(Mapper.convertIntoEntity(techStackDto)));
	}

	public List<TechStackDto> getTechStacks() throws EmployeeManagementException {
		List<TechStackDto> techStacks = Mapper.convertIntoTechStacksDto(techStackRepository.findAll());
		if(!techStacks.isEmpty()) {
			return techStacks;
		} else {
			throw new EmployeeManagementException("No Records Found");
		}
	}

	public TechStackDto getTechStackById(int id) {
		return Mapper.convertIntoDto(techStackRepository.findById(id).orElse(null));
	}

	public String deleteTechStackById(int id) {
		techStackRepository.deleteById(id);
		return "deleted successfully " + id;
	}

	public String updateTechStack(TechStackDto techStackDto, int id) {
		String message = null;
		if (techStackRepository.existsById(id)) {
			TechStackDto existingTechStack = Mapper.convertIntoDto(techStackRepository.findById(id).orElse(null));
			existingTechStack.setName(techStackDto.getName());
			existingTechStack.setVersion(techStackDto.getVersion());
			techStackRepository.save(Mapper.convertIntoEntity(existingTechStack));	
		    message = techStackDto.getName() + " Update Successfully";
		} else {
			message = "TechStack not found";
		}
		return message;
	}

}

