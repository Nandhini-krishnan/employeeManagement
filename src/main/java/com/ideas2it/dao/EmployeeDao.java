package com.ideas2it.dao;

import java.util.Date;
import java.util.List;

import com.ideas2it.model.Employee;
import com.ideas2it.util.exception.EmployeeManagementException;

/**
 * <p>
 * EmployeeDao interface has the methods to handle employee's operations
 * </p>
 *
 * @author Naganandhini
 *
 * @version 1.0 30-AUG-2022
 *
 */
public interface EmployeeDao {

    /**
     * <p>
     * To insert the employee in the database.
     * </p>
     *
     * @param employee - the employee to be inserted
     * @return         - the employee
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    Employee insertEmployee(Employee employee) throws EmployeeManagementException;

    /**
     * <p>
     * To fetch all employees in the database.
     * </p>
     *
     * @return the employee list
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    List<Employee> retrieveEmployees() throws EmployeeManagementException;

    /**
     * <p>
     * To search employees in the database.
     * </p>
     *
     * @return the employee list
     * @throws EmployeeManagementException - if any hibernate exception is occurred
     */
    List<Employee> searchEmployees(String input) throws EmployeeManagementException;

    /**
     * <p>
     * To fetch the employees based on their experience.
     * </p>
     *
     * @return the employee list
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    List<Employee> retrieveEmployeesByExperience(int experience) throws EmployeeManagementException;

    /**
     * <p>
     * To get the employee for the given employee id.
     * </p>
     *
     * @param employeeId - an employee id for which the employee to be returned
     * @return   - the employee if employee id is found
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    Employee retrieveEmployeeById(int employeeId) throws EmployeeManagementException;

    /**
     * <p>
     * To remove the employee for the given employee id.
     * </p>
     *
     * @param employeeId - an employee id to be removed
     * @return   - true if the employee is removed
     *             false if the employee is not found
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    boolean deleteEmployeeById(int employeeId) throws EmployeeManagementException;

    /**
     * <p>
     * To update the employee for the given employee id.
     * </p>
     *
     * @param employee - an employee details to be updated
     * @return   - true if it is updated successfully
     * @throws EmployeeManagementException - if any hibernate exception is occur, when update is unsuccessful
     */
    boolean updateEmployee(Employee employee)throws EmployeeManagementException;

    /**
     * <p>
     * To get the employee count.
     * </p>
     *
     * @return - the employee count
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    Long getEmployeeCount() throws EmployeeManagementException;

    /**
     * <p>
     * To fetch the employees between the range of date of join.
     * </p>
     *
     * @param dateOne - date of join
     * @param dateTwo - date of join
     * @return        - the employee list
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    List<Employee> retrieveEmployeesInRange(Date dateOne, Date dateTwo) throws EmployeeManagementException;

    /**
     * <p>
     * To fetch the employees for the given project id.
     * </p>
     *
     * @param projectId - a project id for which the employee to be returned
     * @return   - the list of employees
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    List<Employee> retrieveEmployeesByProjectId(int projectId) throws EmployeeManagementException;

    /**
     * <p>
     * To fetch the employees for the multiple of given id.
     * </p>
     *
     * @param listOfId - the list of id for which the employee to be returned
     * @return   - the list of filtered employees
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    List<Employee> retrieveEmployeesByMultipleId(List<Integer> listofId) throws EmployeeManagementException;
}