package com.ideas2it.employeemanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.TechStack;
import com.ideas2it.employeemanagement.model.TechStackDto;
import com.ideas2it.employeemanagement.repository.TechStackRepository;
import com.ideas2it.employeemanagement.service.TechStackService;
import com.ideas2it.employeemanagement.util.Constants;
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
		if (techStacks.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND, "404", HttpStatus.NOT_FOUND);
		}
		return techStacks;
	}

	public TechStackDto getTechStackById(int id) throws EmployeeManagementException {
		return Mapper.convertIntoDto(techStackRepository.findById(id).orElseThrow(
				() -> new EmployeeManagementException(Constants.TECH_STACK_NOT_FOUND, "404", HttpStatus.NOT_FOUND)));
	}

	public String deleteTechStackById(int id) throws EmployeeManagementException {
		if (techStackRepository.existsById(id)) {
			techStackRepository.deleteById(id);
		} else {
			throw new EmployeeManagementException(Constants.TECH_STACK_NOT_FOUND, "404", HttpStatus.NOT_FOUND);
		}
		return "deleted successfully " + id;
	}

	public String updateTechStack(TechStackDto techStackDto, int id) throws EmployeeManagementException {
		String message = null;
		if (techStackRepository.existsById(id)) {
			TechStackDto existingTechStack = getTechStackById(id);
			if (null != existingTechStack) {
				existingTechStack.setName(techStackDto.getName());
				existingTechStack.setVersion(techStackDto.getVersion());
				if (existingTechStack.equals(techStackRepository.save(Mapper.convertIntoEntity(techStackDto)))) {
					message = techStackDto.getName() + " Update Successfully";
				} else {
					message = techStackDto.getName() + " Update UnSuccessfull";
				}
			}
		} else {
			throw new EmployeeManagementException(Constants.TECH_STACK_NOT_FOUND, "404", HttpStatus.NOT_FOUND);
		}
		return message;
	}

}
