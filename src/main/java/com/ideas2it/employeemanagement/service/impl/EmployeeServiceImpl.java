package com.ideas2it.employeemanagement.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ideas2it.employeemanagement.model.EmployeeDto;
import com.ideas2it.employeemanagement.repository.EmployeeRepository;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.util.Constants;
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
		if (employees.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND, "404", HttpStatus.NOT_FOUND);			
		} 
		return employees;
	}

	public EmployeeDto getEmployeeById(int id) throws EmployeeManagementException {
		return Mapper.convertIntoDto(employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeManagementException(Constants.EMPLOYEE_NOT_FOUND, "404", HttpStatus.NOT_FOUND)));
	}

	public String deleteEmployeeById(int id) throws EmployeeManagementException {
		if (employeeRepository.existsById(id)) {
		    employeeRepository.deleteById(id);
		} else {
			throw new EmployeeManagementException(Constants.EMPLOYEE_NOT_FOUND, "404", HttpStatus.NOT_FOUND);
		}
		return "deleted successfully " + id;
	}

	public String updateEmployee(EmployeeDto employeeDto, int id) throws EmployeeManagementException {
		String message = null;
		if (employeeRepository.existsById(id)) {
			EmployeeDto existingEmployee = getEmployeeById(id);
			if (null != existingEmployee) {
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
				if (existingEmployee.equals(employeeRepository.save(Mapper.convertIntoEntity(employeeDto)))) {
					message = employeeDto.getName() + " Update Successfully";
				} else {
					message = employeeDto.getName() + " Update UnSuccessfull";
				}
			}
		} else {
			throw new EmployeeManagementException(Constants.EMPLOYEE_NOT_FOUND, "404", HttpStatus.NOT_FOUND);
		}
		return message;
	}

	public String generateEmployeeCode() {
		Long employeeCode = employeeRepository.getEmployeesCount();
		return "EMP" + (++employeeCode);
	}

	public List<EmployeeDto> getEmployeesInRange(Date startDate, Date endDate) throws EmployeeManagementException {
		List<EmployeeDto> employees = Mapper
				.convertIntoEmployeesDto(employeeRepository.findByDateOfJoinBetween(startDate, endDate));
		if (employees.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND, "404", HttpStatus.NOT_FOUND);			
		} 
		return employees;
	}

	public List<EmployeeDto> getEmployeesByMultipleId(List<Integer> listOfId) throws EmployeeManagementException {
		List<EmployeeDto> employees = Mapper.convertIntoEmployeesDto(employeeRepository.findByIdIn(listOfId));
		if (employees.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND, "404", HttpStatus.NOT_FOUND);			
		} 
		return employees;
	}

	public List<EmployeeDto> searchEmployees(String keyword) throws EmployeeManagementException {
		List<EmployeeDto> employees = Mapper.convertIntoEmployeesDto(employeeRepository.searchEmployees(keyword));
		if (employees.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND, "404", HttpStatus.NOT_FOUND);			
		} 
		return employees;
	}
}
