package com.ideas2it.employeemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.repository.EmployeeRepository;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.model.Project;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee insertEmployee(Employee employee) {
		employee.setEmployeeCode(generateEmployeeCode());
		return employeeRepository.save(employee);
	}

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(int id) {
		return employeeRepository.findById(id).orElseThrow();
	}

	public String deleteEmployeeById(int id) {
		employeeRepository.deleteById(id);
		return "deleted successfully " + id;
	}

	public String updateEmployee(Employee employee) {
		String message = null;
		if (employeeRepository.existsById(employee.getId())) {
			Employee existingEmployee = employeeRepository.findById(employee.getId()).orElse(null);
			existingEmployee.setName(employee.getName());
			existingEmployee.setAddress(employee.getAddress());
			existingEmployee.setBloodGroup(employee.getBloodGroup());
			existingEmployee.setDateOfBirth(employee.getDateOfBirth());
			existingEmployee.setDateOfJoin(employee.getDateOfJoin());
			existingEmployee.setExperience(employee.isExperience());
			existingEmployee.setPreviousOrganisationName(employee.getPreviousOrganisationName());
			if (null != employee.getProjects()) {
				List<Project> input = existingEmployee.getProjects();
				input.addAll(employee.getProjects());
				existingEmployee.setProjects(input);
			}
			employeeRepository.save(existingEmployee);
			message = employee.getName() + " Update Successfully";
		} else {
			message = "Employee not found";
		}
		return message;
	}

	public String generateEmployeeCode() {
		Long employeeCode = employeeRepository.count();
		return "EMP" + (++employeeCode);
	}
}
