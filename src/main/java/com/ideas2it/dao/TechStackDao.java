package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.model.TechStack;
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
public interface TechStackDao {

    /**
     * <p>
     * To insert the project in the database.
     * </p>
     *
     * @param employee - the project to be added
     * @return         - inserted project name
     * @throws EmployeeManagementException - if any hibernate exception is thrown
     */
    public TechStack insertTechStack(TechStack techStack) throws EmployeeManagementException;

    /**
     * <p>
     * To fetch all the projects in the database.
     * </p>
     *
     * @return - the project list
     * @throws EmployeeManagementException - if any hibernate exception is thrown
     */
    public List<TechStack> retrieveTechStacks() throws EmployeeManagementException;

    /**
     * <p>
     * To fetch the project for the given employee id.
     * </p>
     *
     * @param id - an employee id for which the projects to be returned
     * @return   - the list of employees
     * @throws EmployeeManagementException - if any hibernate exception is thrown
     */
    public List<TechStack> retrieveTechStacksByProjectId(int projectId) throws EmployeeManagementException;

    /**
     * <p>
     * To fetch the project for the given project id.
     * </p>
     *
     * @param id - a project id for which the project to be returned
     * @return   - the project
     * @throws EmployeeManagementException - if any hibernate exception is thrown
     */
    public TechStack retrieveTechStackById(int techStackId) throws EmployeeManagementException;

    /**
     * <p>
     * To update the tech stack for the given tech stack id.
     * </p>
     *
     * @param techStack - an tech stack details to be updated
     * @return   - true if it is updated successfully
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
     * @throws EmployeeManagementException - if any hibernate exception is thrown
     */
    public boolean deleteTechStackById(int techStackId)throws EmployeeManagementException;
}