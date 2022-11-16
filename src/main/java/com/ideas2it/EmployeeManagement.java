package com.ideas2it;

import java.util.Scanner;

import com.ideas2it.controller.EmployeeController;
import com.ideas2it.controller.ProjectController;
import com.ideas2it.controller.TechStackController;
import com.ideas2it.util.Constants;
import com.ideas2it.util.exception.EmployeeManagementException;

/**
 * <p>
 * Handles the Employee Management
 * </p>
 *
 * @author Naganandhini
 * @version 1.0  10-SEP-2022
 */
public class EmployeeManagement {
    private Scanner scanner = new Scanner(System.in);
    private EmployeeController employeeController = new EmployeeController();
    private ProjectController projectController = new ProjectController();
    private TechStackController techStackController = new TechStackController();

    public static void main(String args[]) {
	new EmployeeManagement().showMenu();
    }

    /**
     * <p>
     * Show the menu and get the choice from the user to perform the process.
     * </p>
     */
    private void showMenu() {
	int choice = 0;
	do {
	    try {
		choice = getInt(Constants.EMPLOYEE_MANAGEMENT_OPTIONS);
		switch (choice) {
		case 1:
		    employeeController.showOptionsForEmployee();
		    break;
		case 2:
		    projectController.showOptionsForProject();
		    break;
		case 3:
		    techStackController.showOptionsForTechStack();
		    break;
		case 4:
		    System.out.println(Constants.EXIT_MESSAGE);
		    break;
		default:
		    System.out.println(Constants.INVALID_OPTION);
		    break;
		}
	    } catch (EmployeeManagementException employeeManagementException) {
		System.out.println(employeeManagementException);
	    }
	} while (4 != choice);
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

}
