package com.ideas2it.service;

import java.util.Date;
import java.util.List;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Project;
import com.ideas2it.util.enumeration.BloodGroup;
import com.ideas2it.util.exception.EmployeeManagementException;

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
     * @param  name - the employee name to be added
     * @param  address - the employee address to be added
     * @param  bloodGroup - the employee blood group to be added
     * @param  dateOfBirth - the employee date of birth to be added
     * @param  hasExperience - the employee experience to be added
     * @param  previousOrganisationName - the employee organisation name to be added
     * @param  domainName - the employee domain name to be added
     * @return the created employee
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    public Employee createEmployee(
	    String name,
	    String address,
	    BloodGroup bloodGroup,
	    Date dateOfBirth,
	    boolean isExperienced,
	    Date dateOfJoin,
	    String previousOrganisationName) throws EmployeeManagementException;

    /**
     * <p>
     * To assign the project for employee.
     * </p>
     *
     * @param employeeId
     * @param projects
     * @return - the employee project
     * @throws EmployeeManagementException - if any hibernate exception is occur, if insertion is unsuccessful
     */
    public Employee assignProjects(int employeeId, List<Project> projects) throws EmployeeManagementException;

    /**
     * <p>
     * Generate the employee id as per the count of the employee
     * </p>
     *
     * @return an employee id with prefix as EMP
     */
    public String generateEmployeeCode() throws EmployeeManagementException;

    /**
     * <p>
     * To fetch all employees in the database.
     * </p>
     *
     * @return - the list of employees
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    public List<Employee> getEmployees() throws EmployeeManagementException;

    /**
     * <p>
     * To get the employee for the given employee id.
     * </p>
     *
     * @param id - an employee id for which the employee to be returned
     * @return   - the employee if employee id is found
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    public Employee getEmployeeById(int employeeId) throws EmployeeManagementException;

    /**
     * <p>
     * To remove the employee for the given employee id.
     * </p>
     *
     * @param id - an employee id to be removed
     * @return   - true if the employee is removed
     *             false if the employee is not found
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    public boolean removeEmployeeById(int employeeId) throws EmployeeManagementException;

    /**
     * <p>
     * This method is used to fetch the employees based on user input.
     * </p>
     *
     * @param input - an input for which employee will be filtered
     * @return      - the list of filtered employees
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    public List<Employee> searchEmployees(String input) throws EmployeeManagementException;

    /**
     * <p>
     * This method is used to fetch the employees based on their experience.
     * </p>
     *
     * @param yearsOfExperience - the experience for which employee will be filtered
     * @return - the list of filtered employees
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    public List<Employee> getEmployeesByExperience(int yearsOfExperience) throws EmployeeManagementException;

    /**
     * <p>
     * This method is used to fetch the employees between the dates of join.
     * </p>
     *
     * @param dateOne - a date for which employee will be filtered
     * @param dateTwo - a date for which employee will be filtered
     * @return        - the list of filtered employees
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    public List<Employee> getEmployeesInRange(Date dateOne,Date dateTwo) throws EmployeeManagementException;

    /**
     * <p>
     * To fetch the employees for the given project id.
     * </p>
     *
     * @param projectId - an project id for which the employee to be returned
     * @return   - the list of filtered employees
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    public List<Employee> getEmployeesByProjectId(int projectId) throws EmployeeManagementException;

    /**
     * <p>
     * To fetch the employees for the multiple of given id.
     * </p>
     *
     * @param listOfId - the list of id for which the employee to be returned
     * @return   - the list of filtered employees
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    public List<Employee> getEmployeesByMultipleId(List<Integer> listOfId) throws EmployeeManagementException;

    /**
     * <p>
     * This method is used to check the existence of employee.
     * </p>
     *
     * @param id - an employee id to be checked
     * @return   - true if the employee is found
     *             false if the employee is not found
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    public boolean isIdExist(int employeeId) throws EmployeeManagementException;

    /**
     * <p>
     * To update the employee for the given employee id.
     * </p>
     *
     * @param  choice     - a choice for which attribute need to be updated
     * @param  employeeId - an employee id to be updated
     * @return            - the updated employee if it is updated
     * @throws EmployeeManagementException - if any hibernate exception is occur, when update is unsuccessful
     */
    public boolean updateEmployee(Employee employee) throws EmployeeManagementException;
}






