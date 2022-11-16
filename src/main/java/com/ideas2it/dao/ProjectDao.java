package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.model.Project;
import com.ideas2it.util.exception.EmployeeManagementException;

/**
 * <p>
 * ProjectDao interface has the methods to handle project's operations
 * </p>
 *
 * @author Naganandhini
 *
 * @version 1.0 19-SEP-2022
 *
 */
public interface ProjectDao {

    /**
     * <p>
     * To insert the project in the database.
     * </p>
     *
     * @param employee - the project to be added
     * @return         - inserted project name
     * @throws EmployeeManagementException - if SQLException is thrown
     */
    public Project insertProject(Project project) throws EmployeeManagementException;

    /**
     * <p>
     * To assign the tech stacks for project.
     * </p>
     *
     * @param techStackId
     * @param projectId
     * @return - true if insertion is successful
     * @throws EmployeeManagementException - if any sql exception is occur, if insertion is unsuccessful
     */
    //public boolean insertTechStackAndProject(int techStackId, int projectId) throws EmployeeManagementException;


    /**
     * <p>
     * To fetch all the projects in the database.
     * </p>
     *
     * @return the project list
     * @throws EmployeeManagementException - if SQLException is thrown
     */
    public List<Project> retrieveProjects() throws EmployeeManagementException;

    /**
     * <p>
     * To fetch the project for the given employee id.
     * </p>
     *
     * @param id - an employee id for which the projects to be returned
     * @return   - the list of employees
     * @throws EmployeeManagementException - if any sql exception is thrown
     */
    public List<Project> retrieveProjectsByEmployeeId(int employeeId) throws EmployeeManagementException;

    /**
     * <p>
     * To fetch the project for the given project id.
     * </p>
     *
     * @param id - a project id for which the project to be returned
     * @return   - the project
     * @throws EmployeeManagementException - if any sql exception is thrown
     */
    public Project retrieveProjectById(int projectId) throws EmployeeManagementException;

    /**
     * <p>
     * To remove the project for the given project id.
     * </p>
     *
     * @param projectId - a project id to be removed
     * @return   - true if the project is removed
                 - false otherwise
     * @throws EmployeeManagementException - if any sql exception is thrown
     */
    public boolean deleteProjectById(int projectId)throws EmployeeManagementException;

    /**
     * <p>
     * To update the project for the given project id.
     * </p>
     *
     * @param project - the project details to be updated
     * @return   - true if it is updated successfully
     * @throws EmployeeManagementException - if any sql exception is occur, when update is unsuccessful
     */
    public boolean updateProject(Project project)throws EmployeeManagementException;
}