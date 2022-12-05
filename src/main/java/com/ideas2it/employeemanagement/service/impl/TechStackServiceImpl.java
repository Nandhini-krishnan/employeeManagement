package com.ideas2it.employeemanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ideas2it.employeemanagement.dto.TechStackDto;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.TechStack;
import com.ideas2it.employeemanagement.repository.TechStackRepository;
import com.ideas2it.employeemanagement.service.TechStackService;
import com.ideas2it.employeemanagement.util.Constants;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.util.mapper.TechStackMapper;

/**
 * <p>
 * TechStackServiceImpl has the methods implementations of TechStackService to
 * handle techStack's operations.
 * </p>
 *
 * @author Naganandhini
 * @version 1.0 10-Aug-2022
 */
@Service
public class TechStackServiceImpl implements TechStackService {

	@Autowired
	private TechStackRepository techStackRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TechStackDto insertTechStack(TechStackDto techStackDto) {
		return TechStackMapper.convertIntoDto(techStackRepository.save(TechStackMapper.convertIntoEntity(techStackDto)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TechStackDto> getTechStacks() throws EmployeeManagementException {
		List<TechStackDto> techStacks = TechStackMapper.convertIntoTechStacksDto(techStackRepository.findAll());
		if (techStacks.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND, "404", HttpStatus.NOT_FOUND);
		}
		return techStacks;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TechStackDto getTechStackById(int id) throws EmployeeManagementException {
		return TechStackMapper.convertIntoDto(techStackRepository.findById(id).orElseThrow(
				() -> new EmployeeManagementException(Constants.TECH_STACK_NOT_FOUND, "404", HttpStatus.NOT_FOUND)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String deleteTechStackById(int id) throws EmployeeManagementException {
		if (techStackRepository.existsById(id)) {
			throw new EmployeeManagementException(Constants.TECH_STACK_NOT_FOUND, "404", HttpStatus.NOT_FOUND);			
		} 
		techStackRepository.deleteById(id);		
		return "deleted successfully " + id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String updateTechStack(TechStackDto techStackDto, int id) throws EmployeeManagementException {
		String message = null;
		if (techStackRepository.existsById(id)) {
			TechStackDto existingTechStack = getTechStackById(id);
			if (null != existingTechStack) {
				existingTechStack.setName(techStackDto.getName());
				existingTechStack.setVersion(techStackDto.getVersion());
				if (existingTechStack.equals(techStackRepository.save(TechStackMapper.convertIntoEntity(techStackDto)))) {
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
