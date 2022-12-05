package com.ideas2it.employeemanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ideas2it.employeemanagement.dto.TechStackDto;
import com.ideas2it.employeemanagement.model.TechStack;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

/**
 *<p>
 * Tech Stack Service has the methods to handle techStack's operations.
 *</p>
 *
 * @author Naganandhini
 * @version 1.0 20-AUG-2022
 */
public interface TechStackService {

	/**
     * <p>
     * To Create the techStack.
     * </p>
     *
     * @param techStackDto - the techStack to be created
     * @return - the created techStack
     * @throws EmployeeManagementException - if the input is invalid
     */
	TechStackDto insertTechStack(TechStackDto techStackDto);

	/**
	 * <p>
	 * To display all the active techStacks stored in the techStacks table.
	 * </p>
	 * 
	 * @return - the list of active techStacks
	 * @throws EmployeeManagementException - when techStacks table is empty
	 */
	List<TechStackDto> getTechStacks() throws EmployeeManagementException;

	/**
     * <p>
     * To get the techStack for the given techStack id.
     * </p>
     *
     * @param id - an techStack id for which the techStack to be returned
     * @return   - the techStack if the techStack id is found
     * @throws EmployeeManagementException - if the techStack id is not found 
     */
	TechStackDto getTechStackById(int id) throws EmployeeManagementException;

	/**
     * <p>
     * To remove the techStack for the given techStack id.
     * </p>
     *
     * @param id - an techStack id to be removed
     * @return   - the success message if the techStack is removed
     * @throws EmployeeManagementException - if the techStack is not found
     */
	String deleteTechStackById(int id) throws EmployeeManagementException;

	/**
     * <p>
     * To update the techStack for the given techStack id.
     * </p>
     *
     * @param  id - an techStack id to be updated
     * @return            - the success message if it is updated
     * @throws EmployeeManagementException - if the techStack is not found, if the techStack is not updated
     */
	String updateTechStack(TechStackDto techStackDto, int id) throws EmployeeManagementException;
}
