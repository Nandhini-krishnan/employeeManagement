package com.ideas2it.employeemanagement.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ideas2it.employeemanagement.repository.EmployeeRepository;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.util.Constants;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.util.mapper.EmployeeMapper;
import com.ideas2it.employeemanagement.dto.EmployeeDto;
import com.ideas2it.employeemanagement.dto.ProjectDto;

/**
 * <p>
 * EmployeeServiceImpl has the methods implementations of EmployeeService to
 * handle employee's operations.
 * </p>
 *
 * @author Naganandhini
 * @version 1.0 10-Aug-2022
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		employeeDto.setEmployeeCode(generateEmployeeCode());
		return EmployeeMapper.convertIntoDto(employeeRepository.save(EmployeeMapper.convertIntoEntity(employeeDto)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EmployeeDto> getEmployees() throws EmployeeManagementException {
		List<EmployeeDto> employees = EmployeeMapper.convertIntoEmployeesDto(employeeRepository.findAll());
		if (employees.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND, "404", HttpStatus.NOT_FOUND);
		}
		return employees;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeDto getEmployeeById(int id) throws EmployeeManagementException {
		return EmployeeMapper.convertIntoDto(employeeRepository.findById(id).orElseThrow(
				() -> new EmployeeManagementException(Constants.EMPLOYEE_NOT_FOUND, "404", HttpStatus.NOT_FOUND)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String deleteEmployeeById(int id) throws EmployeeManagementException {
		if (employeeRepository.existsById(id)) {
			throw new EmployeeManagementException(Constants.EMPLOYEE_NOT_FOUND, "404", HttpStatus.NOT_FOUND);		
		} 
		employeeRepository.deleteById(id);
		return "deleted successfully " + id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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
				if (existingEmployee.equals(employeeRepository.save(EmployeeMapper.convertIntoEntity(employeeDto)))) {
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

	/**
	 * <p>
	 * Generate the employee id as per the count of the employee
	 * </p>
	 *
	 * @return an employee id with prefix as EMP
	 */
	public String generateEmployeeCode() {
		Long employeeCode = employeeRepository.getEmployeesCount();
		return "EMP" + (++employeeCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EmployeeDto> getEmployeesInRange(Date startDate, Date endDate) throws EmployeeManagementException {
		List<EmployeeDto> employees = EmployeeMapper
				.convertIntoEmployeesDto(employeeRepository.findByDateOfJoinBetween(startDate, endDate));
		if (employees.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND, "404", HttpStatus.NOT_FOUND);
		}
		return employees;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EmployeeDto> getEmployeesByMultipleId(List<Integer> listOfId) throws EmployeeManagementException {
		List<EmployeeDto> employees = EmployeeMapper.convertIntoEmployeesDto(employeeRepository.findByIdIn(listOfId));
		if (employees.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND, "404", HttpStatus.NOT_FOUND);
		}
		return employees;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EmployeeDto> searchEmployees(String keyword) throws EmployeeManagementException {
		List<EmployeeDto> employees = Mapper.convertIntoEmployeesDto(employeeRepository.searchEmployees(keyword));
		if (employees.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND, "404", HttpStatus.NOT_FOUND);
		}
		return employees;
	}
}
