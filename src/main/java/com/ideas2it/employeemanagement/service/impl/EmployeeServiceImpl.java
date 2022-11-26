package com.ideas2it.employeemanagement.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.employeemanagement.model.EmployeeDto;
import com.ideas2it.employeemanagement.repository.EmployeeRepository;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.util.mapper.Mapper;
import com.ideas2it.employeemanagement.model.ProjectDto;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeDto insertEmployee(EmployeeDto employeeDto) {
		employeeDto.setEmployeeCode(generateEmployeeCode());
		return Mapper.convertIntoDto(employeeRepository.save(Mapper.convertIntoEntity(employeeDto)));
	}

	public List<EmployeeDto> getEmployees() throws EmployeeManagementException {
		List<EmployeeDto> employees = Mapper.convertIntoEmployeesDto(employeeRepository.findAll());
		if(!employees.isEmpty()) {
			return employees;
		} else {
			throw new EmployeeManagementException("No Records Found");
		}
	}

	public EmployeeDto getEmployeeById(int id) {
		return Mapper.convertIntoDto(employeeRepository.findById(id).orElse(null));
		
	}

	public String deleteEmployeeById(int id) {
		employeeRepository.deleteById(id);	
		return "deleted successfully " + id;
	}

	public String updateEmployee(EmployeeDto employeeDto, int id) {
		String message = null;
		if (employeeRepository.existsById(id)) {
			EmployeeDto existingEmployee = Mapper.convertIntoDto(employeeRepository.findById(id).orElse(null));
			if(null != existingEmployee ) {
			existingEmployee.setName(employeeDto.getName());
			existingEmployee.setAddress(employeeDto.getAddress());
			existingEmployee.setBloodGroup(employeeDto.getBloodGroup());
			existingEmployee.setDateOfBirth(employeeDto.getDateOfBirth());
			existingEmployee.setDateOfJoin(employeeDto.getDateOfJoin());
			existingEmployee.setExperience(employeeDto.isExperience());
			existingEmployee.setPreviousOrganisationName(employeeDto.getPreviousOrganisationName());
			if (null != employeeDto.getProjects()) {
				List<ProjectDto> input = existingEmployee.getProjects();
				input.addAll(employeeDto.getProjects());
				existingEmployee.setProjects(input);
			}
			employeeRepository.save(Mapper.convertIntoEntity(employeeDto));
			message = employeeDto.getName() + " Update Successfully";
			} else {
				message = "Employee not found";
			}
		} else {
			message = "Employee not found";
		}
		return message;
	}

	public String generateEmployeeCode() {
		Long employeeCode = employeeRepository.count();
		return "EMP" + (++employeeCode);
	}
	
	public List<EmployeeDto> getEmployeesInRange(Date startDate, Date endDate) {
		return Mapper.convertIntoEmployeesDto(employeeRepository.findByDateOfJoinBetween(startDate, endDate));
	}
	
	public List<EmployeeDto> getEmployeesByMultipleId(List<Integer> listOfId) {
		return Mapper.convertIntoEmployeesDto(employeeRepository.findByIdIn(listOfId));
	}
	
	public List<EmployeeDto> searchEmployees(String keyword) {
		return Mapper.convertIntoEmployeesDto(employeeRepository.searchEmployees(keyword));
	}
}
