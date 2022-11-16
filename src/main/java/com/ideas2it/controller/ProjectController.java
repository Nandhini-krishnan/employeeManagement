package com.ideas2it.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.model.Project;
import com.ideas2it.model.TechStack;
import com.ideas2it.service.ProjectService;
import com.ideas2it.service.TechStackService;
import com.ideas2it.service.impl.ProjectServiceImpl;
import com.ideas2it.service.impl.TechStackServiceImpl;
import com.ideas2it.util.Constants;
import com.ideas2it.util.DateUtil;
import com.ideas2it.util.Validation;
import com.ideas2it.util.exception.EmployeeManagementException;
import com.ideas2it.util.logger.EmployeeManagementLogger;

/**
 * <p>
 * Gets input for the projects and
 * then displays the results of project's operations.
 * </p>
 *
 * @author Naganandhini
 * version 1.0  19-SEP-2022
 */
public class ProjectController {
    private Scanner scanner = new Scanner(System.in);
    private ProjectService projectService = new ProjectServiceImpl();

    /**
     * <p>
     * Show the menu and get the choice from the user to perform the process.
     * </p>
     */
    public void showOptionsForProject() {
	int choice = 0;
	do {
	    try {
		choice = getInt(Constants.PROJECT_OPTIONS);
		switch (choice) {
		case 1:
		    createProject();
		    break;
		case 2:
		    assignTechStacks();
		    break;
		case 3:
		    showProjects();
		    break;
		case 4:
		    getProject();
		    break;
		case 5:
		    getProjectsByEmployeeId();
		    break;
		case 6:
		    //updateProject();
		    break;
		case 7:
		    removeProject();
		    break;
		case 8:
		    EmployeeManagementLogger.displayInfoLogs(Constants.EXIT_MESSAGE);
		    break;
		default:
		    EmployeeManagementLogger.displayInfoLogs(Constants.INVALID_OPTION);
		    break;
		}
	    } catch (EmployeeManagementException employeeManagementException) {
		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    }
	} while (8 != choice);
    }

    /**
     * Used to get String input.
     *
     * @param message - to display the user context message
     * @return        - the string input
     */
    private String getString(String message) {
	System.out.println(message);
	return scanner.nextLine();
    }

    /**
     * To get integer input from the user and validate the input.
     *
     * @param message - to display user context message
     * @return        - the validated input
     */
    private int getInt(String message) throws EmployeeManagementException {
	int value = 0;
	while (true) {
	    try {
		value = Integer.parseInt(getString(message));
		break;
	    }
	    catch (NumberFormatException numberFormatException) {
		throw new EmployeeManagementException(Constants.INVALID_OPTION);
	    }
	}
	return value;
    }

    /**
     * To get boolean input from the user.
     *
     * @param message - to display user context message
     * @return        - true if the input is one
     *                  false otherwise
     */
    private boolean isChoiceOne(String message) {
	boolean isValid;
	while (true) {
	    try {
		isValid = (1 == getInt(message));
		break;
	    } catch (EmployeeManagementException employeeManagementException) {
		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    }
	}
	return isValid;
    }

    /**
     * Used to get validated name.
     *
     * @param message - to display user context message
     * @return        - the validated name if the name is valid
     *                  to enter valid name otherwise
     */
    private String getValidName(String message) {
	String value = null;
	while (true) {
	    try {
		value = getString(message);
		Validation.isValidName(value);
		break;
	    } catch (EmployeeManagementException employeeManagementException) {
		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    }
	}
	return value;
    }

    /**
     * To get the valid date.
     *
     * @param message - to display user context message
     * @return        - the validated date if it is valid
     *                  to enter valid date otherwise
     */
    private Date getValidDate(String message) {
	while (true) {
	    try {
		String dateOfBirth = getString(message);
		if (Validation.isValidDate(dateOfBirth)) {
		    return DateUtil.getParsedDate(dateOfBirth);
		}
	    }
	    catch (EmployeeManagementException employeeManagementException) {
		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    }
	}
    }

    /**
     * <p>
     * To create the project by getting the project details from user
     * and display project name which is created.
     * </p>
     */
    public void createProject() {
	boolean canContinue;
	do {
	    String name = getValidName(Constants.ENTER_PROJECT_NAME);
	    Date startDate = getValidDate(Constants.ENTER_START_DATE);
	    Date endDate = getValidDate(Constants.ENTER_END_DATE);
	    try {
		EmployeeManagementLogger.displayInfoLogs(projectService.createProject(name,
			startDate,
			endDate).getName() + " Project is created");
	    } catch (EmployeeManagementException employeeManagementException) {
		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    } finally {
		canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
	    }
	} while (canContinue);
    }

    /**
     * <p>
     * To assign the projects for employee.
     * </p>
     */
    private void assignTechStacks() {
	boolean canContinue;
	int projectId = 0;
	do {
	    try {
		projectId = getInt(Constants.ENTER_PROJECT_ID);
		if(projectService.isIdExist(projectId)) {
		    EmployeeManagementLogger.displayInfoLogs(
			    projectService.assignTechStacks(projectId, getTechStacksToAssign()).getName() + Constants.INSERTION_SUCCESSFUL);
		}
	    } catch (EmployeeManagementException employeeManagementException) {
		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    }
	    finally {
		canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
	    }
	} while (canContinue);
    }

    /**
     * <p>
     * To get the projects which will be assigned for employee
     * </p>
     *
     * @return - the list of projects
     *
     */
    private List<TechStack> getTechStacksToAssign() throws EmployeeManagementException {
	TechStackService techStackService = new TechStackServiceImpl();
	boolean canContinue;
	List<TechStack> techStacks = new ArrayList<>();
	int techStackId = 0;
	System.out.println("List of tech stacks are given below.\nIf you don't find any, please create new tech stack");
	techStackService.getTechStacks().forEach(techStack -> System.out.println("id: " + techStack.getId() + "name: "+ techStack.getName()));
	do {
	    try {
		if (isChoiceOne("Press 1 if you find your tech stack \npress any other number to create new tech stack")) {
		    techStackId = getInt(Constants.ENTER_TECH_STACK_ID);
		} else {
		    new ProjectController().createProject();
		    techStackService.getTechStacks().forEach(techStack -> System.out.println("id: " + techStack.getId() + "name: "+ techStack.getName()));
		    techStackId = getInt(Constants.ENTER_TECH_STACK_ID);
		}
		techStacks.add(techStackService.getTechStackById(techStackId));
	    } catch (EmployeeManagementException employeeManagementException) {
		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    }
	    finally {
		canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
	    }
	} while (canContinue);
	return techStacks;
    }

    /**
     * <p>
     * To display all the employees stored in the employees table
     * if the table is empty, display no record found.
     * </p>
     */
    private void showProjects() {
	try {
	    projectService.getProjects().forEach(project -> System.out.println(project));
	} catch (EmployeeManagementException employeeManagementException) {
	    EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	}
    }

    /**
     * <p>
     * Get the project id from the user to get the project.
     * If the projects are found then display the project, otherwise project not found.
     * </p>
     */
    private void getProject() {
	int projectId;
	boolean canContinue;
	do {
	    try {
		projectId = getInt(Constants.ENTER_ID_TO_SEARCH);
		System.out.println(projectService.getProjectById(projectId));
	    }
	    catch (EmployeeManagementException employeeManagementException) {
		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    }
	    finally {
		canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
	    }
	} while (canContinue);
    }

    /**
     * <p>
     * Get the project id from the user to get the projects for that employee.
     * If the projects are found then display the projects, otherwise No Record Found.
     * </p>
     */
    private void getProjectsByEmployeeId() {
	int employeeId;
	boolean canContinue;
	do {
	    try {
		employeeId = getInt(Constants.ENTER_ID_TO_SEARCH);
		projectService.getProjectsByEmployeeId(employeeId).forEach(project -> System.out.println(project));
	    }
	    catch (EmployeeManagementException employeeManagementException) {
		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    }
	    finally {
		canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
	    }
	} while (canContinue);
    }

    /**
     * <p>
     * Get the project id from the user on which project need to be removed.
     * if the project removed successfully then display project is removed, otherwise
     * project not found.
     * </p>
     */
    private void removeProject() throws EmployeeManagementException {
	boolean canContinue;
	do {
	    try {
		if (!(projectService.deleteProjectById(getInt(Constants.ENTER_PROJECT_ID_TO_REMOVE)))) {
		    throw new EmployeeManagementException(Constants.PROJECT_NOT_FOUND);
		}
		EmployeeManagementLogger.displayInfoLogs(Constants.PROJECT_REMOVED);
	    } catch (EmployeeManagementException employeeManagementException) {
		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    }
	    finally {
		canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
	    }
	} while (canContinue);
    }

    /**
     * <p>
     * Get the project id from the user on which project need to be updated.
     * If the project id exist then update the project, otherwise display
     * project not found.
     * </p>
     */
//    private void updateProject() throws EmployeeManagementException {
//	boolean canContinue;
//	int projectId;
//	do {
//	    try {
//		projectId = getInt(Constants.ENTER_PROJECT_ID);
//		if (!(projectService.isIdExist(projectId))) {
//		    throw new EmployeeManagementException(Constants.PROJECT_NOT_FOUND);
//		}
//		updateProjectById(projectId);
//	    } catch (EmployeeManagementException employeeManagementException) {
//		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
//	    }
//	    finally {
//		canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
//	    }
//	} while (canContinue);
//    }

    /**s
     * <p>
     * Get the choice and input from the user on which project detail need to be updated
     * and display the updated project.
     * </p>
     *
     * @param id  an project id to be updated
     */
//    private void updateProjectById(int projectId) {
//	Date startDate = null;
//	Date endDate = null;
//	Project project = null;
//	int choice = 0;
//	do {
//	    try {
//		choice = getInt(Constants.UPDATE_OPTIONS);
//		switch (choice) {
//		case 1:
//		    String name = getValidName(Constants.ENTER_NAME);
//		    project = projectService.updateProjectById(choice, projectId, name);
//		    break;
//		case 2:
//		    startDate = getValidDate(Constants.ENTER_START_DATE);
//		    String dateOne = DateUtil.formatDate(startDate);
//		    project = projectService.updateProjectById(choice, projectId, dateOne);
//		    break;
//		case 3:
//		    endDate = getValidDate(Constants.ENTER_END_DATE);
//		    String dateTwo = DateUtil.formatDate(endDate);
//		    project = projectService.updateProjectById(choice, projectId, dateTwo);
//		    break;
//		case 4:
//		    System.out.println(Constants.EXIT_MESSAGE);
//		    break;
//		default:
//		    System.out.println(Constants.INVALID_OPTION);
//		    break;
//		}
//	    } catch (EmployeeManagementException employeeManagementException) {
//		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
//	    }
//	} while (4 != choice);
//	EmployeeManagementLogger.displayInfoLogs(Constants.PROJECT_UPDATED + project);
//    }
}