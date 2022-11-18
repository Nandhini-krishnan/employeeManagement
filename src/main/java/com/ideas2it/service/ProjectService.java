package com.ideas2it.service;

import java.util.Date;
import java.util.List;

import com.ideas2it.model.Project;
import com.ideas2it.model.TechStack;
import com.ideas2it.util.exception.EmployeeManagementException;

/**
 *<p>
 * ProjectService has the methods to handle projects's operations.
 *</p>
 *
 * @author Naganandhini
 * @version 1.0 19-SEP-2022
 */
public interface ProjectService {

    /**
     * <p>
     * To Create the project.
     * </p>
     *
     * @param  project - the project to be created
     * @return - the created project
     */
    public Project createProject(Project project) throws EmployeeManagementException;

    /**
     * <p>
     * To assign the tech stacks for project.
     * </p>
     *
     * @param techStackId
     * @param projectId
     * @return - true if insertion is successful
     * @throws EmployeeManagementException - if any hibernate exception is occur, if insertion is unsuccessful
     */
    public Project assignTechStacks(int projectId, List<TechStack> techStacks) throws EmployeeManagementException;

    /**
     * <p>
     * To fetch all the projects in the project table.
     * </p>
     *
     * @return - the list of projects
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    public List<Project> getProjects() throws EmployeeManagementException;

    /**
     * <p>
     * To fetch the project for the given project id
     * </p>
     *
     * @param projectId - a project id for which the project to be returned
     * @return - the project
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    public Project getProjectById(int projectId) throws EmployeeManagementException;

    /**
     * <p>
     * To fetch the projects for the given employee id.
     * </p>
     *
     * @param employeeId - an employee id for which the projects to be returned
     * @return - the list of projects
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    public List<Project> getProjectsByEmployeeId(int employeeId) throws EmployeeManagementException;

    /**
     * <p>
     * To check the existence of project.
     * </p>
     *
     * @param projectId - an projectId id to be checked
     * @return   - true if the project is found
     *             false if the project is not found
     * @throws EmployeeManagementException - if any hibernate exception is occur
     */
    public boolean isIdExist(int projectId) throws EmployeeManagementException;

    /**
     * <p>
     * To update the project for the given project id.
     * </p>
     *
     * @param  project  - the project details to be updated
     * @return          - true if it is updated successfully
     * @throws EmployeeManagementException - if any hibernate exception is occur, when update is unsuccessful
     */
    public boolean updateProject(Project project) throws EmployeeManagementException;

    /**
     * <p>
     * To remove the project for the given project id.
     * </p>
     *
     * @param projectId - a project id to be removed
     * @return   - true if the project is removed
                 - false otherwise
     * @throws EmployeeManagementException - if any  hibernate exception is thrown
     */
    public boolean deleteProjectById(int projectId) throws EmployeeManagementException;
}






