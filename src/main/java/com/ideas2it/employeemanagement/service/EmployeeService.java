package com.ideas2it.employeemanagement.service;

import java.util.Date;
import java.util.List;

import com.ideas2it.employeemanagement.dto.EmployeeDto;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

/**
 *<p>
 * EmployeeService has the methods to handle employee's operations.
 *</p>
 *
 * @author Naganandhini
 * @version 1.0 20-AUG-2022
 */
public interface EmployeeService {

	/**
     * <p>
     * To Create the employee.
     * </p>
     *
     * @param employeeDto - the employee to be created
     * @return - the created employee 
     * @throws EmployeeManagementException - if the input is invalid
     */
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	
	/**
	 * <p>
	 * To display all the active employees stored in the employees table.
	 * </p>
	 * 
	 * @return - the list of active employees
	 * @throws EmployeeManagementException - when employees table is empty
	 */
    List<EmployeeDto> getEmployees() throws EmployeeManagementException;
	
    /**
     * <p>
     * To get the employee for the given employee id.
     * </p>
     *
     * @param id - an employee id for which the employee to be returned
     * @return   - the employee if the employee id is found
     * @throws EmployeeManagementException - if the employee id is not found 
     */
    EmployeeDto getEmployeeById(int id) throws EmployeeManagementException;
    
    /**
     * <p>
     * To remove the employee for the given employee id.
     * </p>
     *
     * @param id - an employee id to be removed
     * @return   - the success message with HttpStatus.Ok
     * @throws EmployeeManagementException - if the employee is not found
     */
	String deleteEmployeeById(int id) throws EmployeeManagementException;

	/**
     * <p>
     * To update the employee for the given employee id.
     * </p>
     *
     * @param  employeeId - an employee id to be updated
     * @return            - the success message if it is updated
     * @throws EmployeeManagementException - if the employee is not found, if the employee is not updated
     */
	String updateEmployee(EmployeeDto employeeDto, int id) throws EmployeeManagementException;
	
	/**
     * <p>
     * To fetch the employees between the range of date.
     * </p>
     *
     * @param startDate - a start date for which employee will be filtered
     * @param endDate - an end date for which employee will be filtered
     * @return        - the list of filtered employees
     * @throws EmployeeManagementException - if there is no employee based on the given range
     */
	List<EmployeeDto> getEmployeesInRange(Date startDate, Date endDate) throws EmployeeManagementException;
	
	/**
     * <p>
     * To fetch the employees for the multiple given id.
     * </p>
     *
     * @param listOfId - the list of id for which the employee to be returned
     * @return   - the list of filtered employees
     * @throws EmployeeManagementException - if there is no employee for the given id
     */
	List<EmployeeDto> getEmployeesByMultipleId(List<Integer> listOfId) throws EmployeeManagementException;
	
	/**
     * <p>
     * To search the employees.
     * </p>
     *
     * @param keyword - an input for which employee will be filtered
     * @return - the list of filtered employees
     * @throws EmployeeManagementException - if there is no employee based on the given condition
     */
	List<EmployeeDto> searchEmployees(String keyword) throws EmployeeManagementException;		
}
