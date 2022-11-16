package com.ideas2it.service.impl;

import java.util.Date;
import java.util.List;

import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.dao.impl.EmployeeDaoImpl;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Project;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.util.Constants;
import com.ideas2it.util.DateUtil;
import com.ideas2it.util.enumeration.BloodGroup;
import com.ideas2it.util.exception.EmployeeManagementException;

/**
 * <p>
 * EmployeeServiceImpl has the methods implementations of EmployeeService to
 * handle employee's operations.
 * </p>
 *
 * @author Naganandhini
 * @version 1.0 10-Aug-2022
 */
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao = new EmployeeDaoImpl();

	/**
	 * {@inheritdoc}
	 */
	@Override
	public Employee createEmployee(Employee employee)
			throws EmployeeManagementException {;
		employee.setEmployeeCode(generateEmployeeCode());
		return employeeDao.insertEmployee(employee);
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public Employee assignProjects(int employeeId, List<Project> projects) throws EmployeeManagementException {
		Employee employee = getEmployeeById(employeeId);
		List<Project> input = employee.getProjects();
		input.addAll(projects);
		employee.setProjects(input);
		return employeeDao.insertEmployee(employee);
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public String generateEmployeeCode() throws EmployeeManagementException {
		Long employeeCode = employeeDao.getEmployeeCount();
		return "EMP" + (++employeeCode);
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Employee> getEmployees() throws EmployeeManagementException {
		List<Employee> employees = employeeDao.retrieveEmployees();
		if (employees.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND);
		}
		return employees;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public Employee getEmployeeById(int employeeId) throws EmployeeManagementException {
		Employee employee = employeeDao.retrieveEmployeeById(employeeId);
		if (null == employee) {
			throw new EmployeeManagementException(Constants.EMPLOYEE_NOT_FOUND);
		}
		return employee;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public boolean removeEmployeeById(int employeeId) throws EmployeeManagementException {
		return employeeDao.deleteEmployeeById(employeeId);
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Employee> searchEmployees(String input) throws EmployeeManagementException {
		List<Employee> employees = employeeDao.searchEmployees(input);
		if (employees.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND);
		}
		return employees;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Employee> getEmployeesByExperience(int experience) throws EmployeeManagementException {
		List<Employee> employees = employeeDao.retrieveEmployeesByExperience(experience);
		if (employees.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND);
		}
		return employees;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Employee> getEmployeesInRange(Date dateOne, Date dateTwo) throws EmployeeManagementException {
		List<Employee> employees = employeeDao.retrieveEmployeesInRange(dateOne, dateTwo);
		if (employees.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND);
		}
		return employees;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Employee> getEmployeesByProjectId(int projectId) throws EmployeeManagementException {
		List<Employee> employees = employeeDao.retrieveEmployeesByProjectId(projectId);
		if (employees.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND);
		}
		return employees;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Employee> getEmployeesByMultipleId(List<Integer> listOfId) throws EmployeeManagementException {
		List<Employee> employees = employeeDao.retrieveEmployeesByMultipleId(listOfId);
		if (employees.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND);
		}
		return employees;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public boolean isIdExist(int employeeId) throws EmployeeManagementException {
		if (null != getEmployeeById(employeeId)) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public boolean updateEmployee(Employee employee) throws EmployeeManagementException {
	    boolean isUpdated = employeeDao.updateEmployee(employee);
	    if (!isUpdated) {
		    throw new EmployeeManagementException("Employee not updated");
	    }
	    return isUpdated;
	}
	

	/**
	 * {@inheritdoc}
	 */
//	@Override
//	public Employee updateEmployeeById(int choice, int employeeId, String attribute)
//			throws EmployeeManagementException {
//		Employee employee;
//		employee = getEmployeeById(employeeId);
//		switch (choice) {
//		case 1:
//			employee.setName(attribute);
//			break;
//		case 2:
//			employee.setAddress(attribute);
//			break;
//		case 3:
//			BloodGroup bloodGroup = BloodGroup.valueOf(attribute);
//			employee.setBloodGroup(bloodGroup);
//			break;
//		case 4:
//			Date dateOfBirth = DateUtil.getParsedDate(attribute);
//			employee.setDateOfBirth(dateOfBirth);
//			break;
//		case 5:
//			boolean hasExperience = Boolean.parseBoolean(attribute);
//			employee.setExperience(hasExperience);
//			break;
//		case 6:
//			Date dateOfJoining = DateUtil.getParsedDate(attribute);
//			employee.setDateOfJoin(dateOfJoining);
//			break;
//		}
//		return employeeDao.updateEmployeeById(employee, employeeId);
//	}
}
