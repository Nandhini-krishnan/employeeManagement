package com.ideas2it.employeemanagement.service;

import java.util.List;

import com.ideas2it.employeemanagement.dto.ProjectDto;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

/**
 *<p>
 * ProjectService has the methods to handle project's operations.
 *</p>
 *
 * @author Naganandhini
 * @version 1.0 20-AUG-2022
 */
public interface ProjectService {

	/**
     * <p>
     * To Create the project.
     * </p>
     *
     * @param projectDto - the project to be created
     * @return - the created project
     * @throws EmployeeManagementException - if the input is invalid
     */
	ProjectDto createProject(ProjectDto projectDto) throws EmployeeManagementException;

	/**
	 * <p>
	 * To display all the active projects stored in the projects table.
	 * </p>
	 * 
	 * @return - the list of active projects
	 * @throws EmployeeManagementException - when projects table is empty
	 */
	List<ProjectDto> getProjects() throws EmployeeManagementException;

	/**
     * <p>
     * To get the project for the given project id.
     * </p>
     *
     * @param id - an project id for which the project to be returned
     * @return   - the project if the project id is found
     * @throws EmployeeManagementException - if the project id is not found 
     */
	ProjectDto getProjectById(int id) throws EmployeeManagementException;

	/**
     * <p>
     * To remove the project for the given project id.
     * </p>
     *
     * @param id - an project id to be removed
     * @return   - the success message if the project is removed
     * @throws EmployeeManagementException - if the project is not found
     */
	String deleteProjectById(int id) throws EmployeeManagementException;

	/**
     * <p>
     * To update the project for the given project id.
     * </p>
     *
     * @param  id - an project id to be updated
     * @return            - the success message if it is updated
     * @throws EmployeeManagementException - if the project is not found, if the project is not updated
     */
	String updateProject(ProjectDto projectDto, int id) throws EmployeeManagementException;
}
