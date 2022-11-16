package com.ideas2it.controller;

import java.util.Date;
import java.util.Scanner;

import com.ideas2it.model.TechStack;
import com.ideas2it.service.TechStackService;
import com.ideas2it.service.impl.TechStackServiceImpl;
import com.ideas2it.util.Constants;
import com.ideas2it.util.DateUtil;
import com.ideas2it.util.Validation;
import com.ideas2it.util.exception.EmployeeManagementException;
import com.ideas2it.util.logger.EmployeeManagementLogger;

public class TechStackController {
    private Scanner scanner = new Scanner(System.in);
    private TechStackService techStackService = new TechStackServiceImpl();

    /**
     * <p>
     * Show the menu and get the choice from the user to perform the process.
     * </p>
     */
    public void showOptionsForTechStack() {
	int choice = 0;
	do {
	    try {
		choice = getInt(Constants.TECH_STACK_OPTIONS);
		switch (choice) {
		case 1:
		    createTechStack();
		    break;
		case 2:
		    showTechStacks();
		    break;
		case 3:
		    getTechStack();
		    break;
		case 4:
		    getTechStacksByProjectId();
		    break;
		case 5:
		    removeTechStack();
		    break;
		case 6:
		   // updateTechStack();
		    break;
		case 7:
		    EmployeeManagementLogger.displayInfoLogs(Constants.EXIT_MESSAGE);
		    break;
		default:
		    EmployeeManagementLogger.displayInfoLogs(Constants.INVALID_OPTION);
		    break;
		}
	    } catch (EmployeeManagementException employeeManagementException) {
		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    }
	} while (7 != choice);
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
     * This method is used to get integer input from the user and validate the input.
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
     * This method is used to get boolean input from the user.
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
     * Used to get validated version.
     *
     * @param message - to display user context message
     * @return        - the validated version if the version is valid
     *                  to enter valid version otherwise
     */
    private float getValidVersion(String message) {
	System.out.println(message);
	float value = 0;
	while (true) {
	    try {
		value = scanner.nextFloat();
		Validation.isValidVersion(value);
		break;
	    } catch (EmployeeManagementException employeeManagementException) {
		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    }
	}
	return value;
    }

    /**
     * This method is used to return valid date.
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

    public void createTechStack() {
	boolean canContinue;
	do {
	    String name = getValidName(Constants.ENTER_TECHNOLOGY_NAME);
	    float version = getValidVersion(Constants.ENTER_VERSION);
	    try {
		EmployeeManagementLogger.displayInfoLogs(techStackService.createTechStack(
			name,
			version) + "TechStack is created");
	    } catch (EmployeeManagementException employeeManagementException) {
		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	    } finally {
		canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
	    }
	} while (canContinue);
    }

    /**
     * <p>
     * To assign the projects for tech Stack.
     * </p>
     */
    //    private void assignProjects() {
    //	ProjectService projectService = new ProjectServiceImpl();
    //	boolean canContinue;
    //	int projectId = 0;
    //	int techStackId = 0;
    //	do {
    //	    try {
    //		techStackId = getInt(Constants.ENTER_TECH_STACK_ID);
    //		System.out.println("List of projects are given below:");
    //		projectService.getProjects().forEach(project -> System.out.println("ID: " + project.getId()
    //		+ " NAME: " + project.getName()));
    //		projectId = getInt(Constants.ENTER_PROJECT_ID);
    //		if(projectService.assignTechStacks(techStackId, projectId)) {
    //		    EmployeeManagementLogger.displayInfoLogs("Insertion successful");
    //		}
    //	    } catch (EmployeeManagementException employeeManagementException) {
    //		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
    //	    }
    //	    finally {
    //		canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
    //	    }
    //	} while (canContinue);
    //    }

    /**
     * <p>
     * To display all the tech stacks stored in the tech stack table.
     * if the table is empty, dispay no record found.
     * </p>
     */
    private void showTechStacks() {
	try {
	    techStackService.getTechStacks().forEach(techStack -> System.out.println(techStack));
	} catch (EmployeeManagementException employeeManagementException) {
	    EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
	}
    }

    /**
     * <p>
     * Get the employee id from the user to search the employee.
     * If the employee is found then display the employee, otherwise employee not found.
     * </p>
     */
    private void getTechStack() {
	int techStackId;
	boolean canContinue;
	do {
	    try {
		techStackId = getInt(Constants.ENTER_TECH_STACK_ID);
		System.out.println(techStackService.getTechStackById(techStackId));
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
     * Get the employee id from the user to search the employee.
     * If the employee is found then display the employee, otherwise employee not found.
     * </p>
     */
    private void getTechStacksByProjectId() {
	int projectId;
	boolean canContinue;
	do {
	    try {
		projectId = getInt(Constants.ENTER_ID_TO_SEARCH);
		techStackService.getTechStacksByProjectId(projectId).forEach(techStack -> System.out.println(techStack));
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
     * Get the employee id from the user on which employee need to be removed.
     * if the project removed successfully then display employee is removed, otherwise
     * employee not found.
     * </p>
     */
    private void removeTechStack() throws EmployeeManagementException {
	boolean canContinue;
	do {
	    try {
		if (!(techStackService.removeTechStackById(getInt(Constants.ENTER_TECH_STACK_ID)))) {
		    throw new EmployeeManagementException(Constants.TECH_STACK_NOT_FOUND);
		}
		EmployeeManagementLogger.displayInfoLogs(Constants.TECH_STACK_REMOVED);
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
     * Get the tech stack id from the user on which tech stack need to be updated.
     * If the tech stack id exist then update the tech stack, otherwise display
     * tech stack not found.
     * </p>
     */
//    private void updateTechStack() throws EmployeeManagementException {
//	boolean canContinue;
//	int techStackId;
//	do {
//	    try {
//		techStackId = getInt(Constants.ENTER_TECH_STACK_ID);
//		if (!(techStackService.isIdExist(techStackId))) {
//		    throw new EmployeeManagementException(Constants.TECH_STACK_NOT_FOUND);
//		}
//		updateTechStackById(techStackId);
//	    } catch (EmployeeManagementException employeeManagementException) {
//		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
//	    }
//	    finally {
//		canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
//	    }
//	} while (canContinue);
//    }

    /**
     * <p>
     * Get the choice and input from the user on which tech stack detail need to be updated
     * and display the updated tech stack.
     * </p>
     *
     * @param id  an tech stack id to be updated
     */
//    private void updateTechStackById(int techStackId) {
//	float version = 0;
//	TechStack techStack = null;
//	int choice = 0;
//	do {
//	    try {
//		choice = getInt(Constants.UPDATE_OPTIONS);
//		switch (choice) {
//		case 1:
//		    String name = getValidName(Constants.ENTER_NAME);
//		    techStack = techStackService.updateTechStackById(choice, techStackId, name);
//		    break;
//		case 2:
//		    version = getValidVersion(Constants.ENTER_VERSION);
//		    String value = String.valueOf(version);
//		    techStack = techStackService.updateTechStackById(choice, techStackId, value);
//		    break;
//		case 3:
//		    System.out.println(Constants.EXIT_MESSAGE);
//		    break;
//		default:
//		    System.out.println(Constants.INVALID_OPTION);
//		    break;
//		}
//	    } catch (EmployeeManagementException employeeManagementException) {
//		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
//	    }
//	} while (3 != choice);
//	EmployeeManagementLogger.displayInfoLogs(Constants.TECH_STACK_UPDATED + techStack);
//    }
}