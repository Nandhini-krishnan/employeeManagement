package com.ideas2it.service;

import java.util.List;

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
public interface TechStackService {

    /**
     * <p>
     * To Create the project.
     * </p>
     *
     * @param  name - the project name to be added
     * @param  startDate - the start date of the project to be added
     * @param  endDate - the end date of the project to be added
     * @return - the created project
     */
    public TechStack createTechStack(
	    String name,
	    float version) throws EmployeeManagementException;

    /**
     * <p>
     * To fetch all the projects in the project table.
     * </p>
     *
     * @return - the list of projects
     */
    public List<TechStack> getTechStacks() throws EmployeeManagementException;

    /**
     * <p>
     * To fetch the project for the given project id
     * </p>
     *
     * @param projectId - a project id for which the project to be returned
     * @return - the project
     */
    public TechStack getTechStackById(int techStackId) throws EmployeeManagementException;

    /**
     * <p>
     * To fetch the projects for the given employee id.
     * </p>
     *
     * @param employeeId - an employee id for which the projects to be returned
     * @return - the list of projects
     */
    public List<TechStack> getTechStacksByProjectId(int projectId) throws EmployeeManagementException;

    /**
     * <p>
     * To check the existence of tech stack.
     * </p>
     *
     * @param projectId - an tech stack id to be checked
     * @return   - true if the tech stack is found
     *             false if the tech stack is not found
     * @throws EmployeeManagementException - if any sql exception is occur
     */
    public boolean isIdExist(int techStackId) throws EmployeeManagementException;

    /**
     * <p>
     * To update the tech stack for the given tech stack id.
     * </p>
     *
     * @param  techStack  - the tech stack details to be updated
     * @return            - true if it is updated successfully
     * @throws EmployeeManagementException - if any hibernate exception is occur, when update is unsuccessful
     */
    public boolean updateTechStack(TechStack techStack) throws EmployeeManagementException;

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
    public boolean removeTechStackById(int techStackId) throws EmployeeManagementException;
}






