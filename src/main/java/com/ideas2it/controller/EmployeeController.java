package com.ideas2it.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Project;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.ProjectService;
import com.ideas2it.service.impl.EmployeeServiceImpl;
import com.ideas2it.service.impl.ProjectServiceImpl;
import com.ideas2it.util.Constants;
import com.ideas2it.util.DateUtil;
import com.ideas2it.util.Validation;
import com.ideas2it.util.enumeration.BloodGroup;
import com.ideas2it.util.exception.EmployeeManagementException;
import com.ideas2it.util.logger.EmployeeManagementLogger;

/**
 * <p>
 * Gets input for the employees and then displays the results of employee's
 * operations.
 * </p>
 *
 * @author Naganandhini version 1.0 10-AUG-2022
 */
public class EmployeeController {
	private Scanner scanner = new Scanner(System.in);
	private ProjectController projectController = new ProjectController();
	private EmployeeService employeeService = new EmployeeServiceImpl();
	private ProjectService projectService = new ProjectServiceImpl();

	/**
	 * <p>
	 * Show the menu and get the choice from the user to perform the employee
	 * process.
	 * </p>
	 */
	public void showOptionsForEmployee() {
		int choice = 0;
		do {
			try {
				choice = getInt(Constants.EMPLOYEE_OPTIONS);
				switch (choice) {
				case 1:
					createEmployee();
					break;
				case 2:
					assignProjects();
					break;
				case 3:
					showEmployees();
					break;
				case 4:
					searchEmployees();
					break;
				case 5:
					getEmployeesByExperience();
					break;
				case 6:
					getEmployeesBetweenDateOfJoin();
					break;
				case 7:
					getEmployeesByProjectId();
					break;
				case 8:
					getEmployeesByMultipleId();
					break;
				case 9:
					getEmployee();
					break;
				case 10:
					removeEmployee();
					break;
				case 11:
					updateEmployee();
					break;
				case 12:
					EmployeeManagementLogger.displayInfoLogs(Constants.EXIT_MESSAGE);
					break;
				default:
					EmployeeManagementLogger.displayInfoLogs(Constants.INVALID_OPTION);
					break;
				}
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			}
		} while (12 != choice);
	}

	/**
	 * Used to get String input.
	 *
	 * @param message - to display the user context message
	 * @return - the string input
	 */
	private String getString(String message) {
		System.out.println(message);
		return scanner.nextLine();
	}

	/**
	 * Used to get validated name.
	 *
	 * @param message - to display user context message
	 * @return - the validated name if the name is valid to enter valid name
	 *         otherwise
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
	 * Used to get validated Address.
	 *
	 * @param message - to display user context message
	 * @return - the validated address if the address is valid to enter valid
	 *         address otherwise
	 */
	private String getValidAddress(String message) {
		String value = null;
		while (true) {
			try {
				value = getString(message);
				Validation.isValidAddress(value);
				break;
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			}
		}
		return value;
	}

	/**
	 * To get the validated organisation name.
	 *
	 * @param message - to display user context message
	 * @return - the validated organisation name if the organisation name is valid
	 *         to enter valid organisation name otherwise
	 */
	private String getValidOrganisationName(String message) {
		String value = null;
		while (true) {
			try {
				value = getString(message);
				Validation.isValidOrganisationName(value);
				break;
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			}
		}
		return value;
	}

	/**
	 * To get the validated domain name.
	 *
	 * @param message - to display user context message
	 * @return - the validated domain name if the domain name is valid to enter
	 *         valid domain name otherwise
	 */
	private String getValidDomainName(String message) {
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
	 * To get the validated date.
	 *
	 * @param message - to display user context message
	 * @return - the validated date if it is valid to enter valid date otherwise
	 */
	private Date getValidDate(String message) {
		while (true) {
			try {
				String dateOfBirth = getString(message);
				if (Validation.isValidDate(dateOfBirth)) {
					return DateUtil.getParsedDate(dateOfBirth);
				}
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			}
		}
	}

	/**
	 * To get the valid date of birth.
	 *
	 * @param message - to display user context message
	 * @return - the validated date of birth if it is valid to enter valid date
	 *         otherwise
	 */
	private Date getValidDateOfBirth(String message) {
		Date result = null;
		while (true) {
			try {
				result = getValidDate(message);
				if (DateUtil.isValidAge(result)) {
					return result;
				}
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			}
		}
	}

	/**
	 * To get the valid date of joining.
	 *
	 * @param message - to display user context message
	 * @return - the validated date if the date is valid to enter the valid date
	 *         otherwise
	 */
	private Date getValidDateOfJoining(String message, Date dateOfBirth) {
		Date dateOfJoining = null;
		while (true) {
			try {
				dateOfJoining = getValidDate(message);
				if (DateUtil.compareTwoDates(dateOfJoining, dateOfBirth)) {
					return dateOfJoining;
				}
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			}
		}
	}

	/**
	 * To get boolean input from the user.
	 *
	 * @param message - to display user context message
	 * @return - true if the input is one false otherwise
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
	 * To get integer input from the user and validate the input.
	 *
	 * @param message - to display user context message
	 * @return - the validated input
	 */
	private int getInt(String message) throws EmployeeManagementException {
		int value = 0;
		while (true) {
			try {
				value = Integer.parseInt(getString(message));
				break;
			} catch (NumberFormatException numberFormatException) {
				throw new EmployeeManagementException(Constants.INVALID_OPTION);
			}
		}
		return value;
	}

	/**
	 * To get the valid bloodgroup.
	 *
	 * @return - the validated bloodgroup if it is valid to choose valid bloodgroup
	 *         otherwise
	 */
	private BloodGroup getValidBloodGroup() {
		BloodGroup result = null;
		String message = Constants.BLOODGROUP_OPTIONS;
		while (true) {
			try {
				result = BloodGroup.getBloodGroup(getInt(message));
				break;
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			}
		}
		return result;
	}

	/**
	 * <p>
	 * To create the employee by getting the employee details from user and display
	 * employee name which is created.
	 * </p>
	 */
	private void createEmployee() {
		boolean canContinue;
		do {
			String name = getValidName(Constants.ENTER_NAME);
			String address = getValidAddress(Constants.ENTER_ADDRESS);
			BloodGroup bloodGroup = getValidBloodGroup();
			Date dateOfBirth = getValidDateOfBirth(Constants.ENTER_DATEOFBIRTH);
			Date dateOfJoin = getValidDateOfJoining(Constants.ENTER_DATEOFJOINING, dateOfBirth);
			boolean isExperienced = isChoiceOne(Constants.ENTER_EXPERIENCE);
			String previousOrganisationName = null;
			if (isExperienced) {
				previousOrganisationName = getValidOrganisationName(Constants.ENTER_ORGANISATION_NAME);
			}
			try {
				EmployeeManagementLogger
						.displayInfoLogs(
								employeeService
										.createEmployee(name, address, bloodGroup, dateOfBirth, isExperienced,
												dateOfJoin, previousOrganisationName)
										.getName() + Constants.EMPLOYEE_CREATED);
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
	private void assignProjects() {
		boolean canContinue;
		int employeeId = 0;
		do {
			try {
				employeeId = getInt(Constants.ENTER_EMPLOYEE_ID_TO_ASSIGN_PROJECT);
				if (employeeService.isIdExist(employeeId)) {
					EmployeeManagementLogger
							.displayInfoLogs(employeeService.assignProjects(employeeId, getProjectsToAssign()).getName()
									+ Constants.INSERTION_SUCCESSFUL);
				}
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			} finally {
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
	private List<Project> getProjectsToAssign() throws EmployeeManagementException {
		boolean canContinue;
		List<Project> projects = new ArrayList<>();
		int projectId = 0;
		System.out.println("List of projects are given below.\nIf you don't find any, please create new project");
		projectService.getProjects()
				.forEach(project -> System.out.println("id: " + project.getId() + "name: " + project.getName()));
		do {
			try {
				if (isChoiceOne("Press 1 if you find your project \npress any other number to create new project")) {
					projectId = getInt(Constants.ENTER_PROJECT_ID);
				} else {
					projectController.createProject();
					projectService.getProjects().forEach(
							project -> System.out.println("id: " + project.getId() + "name: " + project.getName()));
					projectId = getInt(Constants.ENTER_PROJECT_ID);
				}
				projects.add(projectService.getProjectById(projectId));

			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			} finally {
				canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
			}
		} while (canContinue);
		return projects;
	}

	/**
	 * <p>
	 * To display all the employees stored in the employees table. if the employees
	 * table is empty, dispay no record found.
	 * </p>
	 */
	private void showEmployees() {
		try {
			List<Employee> employees = employeeService.getEmployees();
			employeeService.getEmployees().forEach(employee -> System.out.println(employee));
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	/**
	 * <p>
	 * To get the employees by experience and display the employees.
	 * </p>
	 */
	private void getEmployeesByExperience() {
		List<Employee> employees;
		boolean canContinue;
		do {
			try {
				employees = employeeService
						.getEmployeesByExperience(getInt(Constants.ENTER_EMPLOYEE_EXPERIENCE_TO_SEARCH));
				employees.forEach(employee -> System.out.println(employee));
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			} finally {
				canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
			}
		} while (canContinue);
	}

	/**
	 * <p>
	 * To get the employees by multiple id and display the employees.
	 * </p>
	 */
	private void getEmployeesByMultipleId() {
		List<Employee> employees;
		boolean canContinue;
		do {
			try {
				int sizeOfId = getInt(Constants.ENTER_NUMBER_OF_ID);
				employees = employeeService.getEmployeesByMultipleId(getListOfId(sizeOfId));
				employees.forEach(employee -> System.out.println(employee));
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			} finally {
				canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
			}
		} while (canContinue);
	}

	/**
	 * <p>
	 * To get the multiple id of employees
	 * </p>
	 *
	 * @param sizeOfInput - number of id
	 * @return - the list of id
	 *
	 */
	private List<Integer> getListOfId(int sizeOfInput) throws EmployeeManagementException {
		List<Integer> listOfId = new ArrayList<>();
		for (int index = 0; index < sizeOfInput; index++) {
			listOfId.add(getInt((index + 1) + Constants.ENTER_EMPLOYEE_ID));
		}
		return listOfId;
	}

	/**
	 * <p>
	 * To get the employees by the range of date of join and display the employees.
	 * </p>
	 */
	private void getEmployeesBetweenDateOfJoin() {
		List<Employee> employees;
		boolean canContinue;
		do {
			try {
				employees = employeeService.getEmployeesInRange(getValidDate(Constants.ENTER_FIRST_DATE_TO_SEARCH),
						getValidDate(Constants.ENTER_SECOND_DATE_TO_SEARCH));
				employees.forEach(employee -> System.out.println(employee));
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			} finally {
				canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
			}
		} while (canContinue);
	}

	/**
	 * <p>
	 * To get the employees by their project id and display the employees.
	 * </p>
	 */
	private void getEmployeesByProjectId() {
		List<Employee> employees;
		boolean canContinue;
		do {
			try {
				employees = employeeService.getEmployeesByProjectId(getInt(Constants.ENTER_PROJECT_ID));
				employees.forEach(employee -> System.out.println(employee));
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			} finally {
				canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
			}
		} while (canContinue);
	}

	/**
	 * <p>
	 * To search the employees by name and display the employees.
	 * </p>
	 */
	private void searchEmployees() {
		List<Employee> employees;
		boolean canContinue;
		do {
			try {
				employees = employeeService.searchEmployees("%" + getString(Constants.ENTER_NAME_TO_SEARCH) + "%");
				employees.forEach(employee -> System.out.println(employee));
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			} finally {
				canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
			}
		} while (canContinue);
	}

	/**
	 * <p>
	 * Get the employee id from the user to search the employee. If the employee is
	 * found then display the employee, otherwise employee not found.
	 * </p>
	 */
	private void getEmployee() {
		int employeeId;
		boolean canContinue;
		do {
			try {
				employeeId = getInt(Constants.ENTER_ID_TO_SEARCH);
				System.out.println(employeeService.getEmployeeById(employeeId));
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			} finally {
				canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
			}
		} while (canContinue);
	}

	/**
	 * <p>
	 * Get the employee id from the user on which employee need to be removed. if
	 * the employee removed successfully then display employee is removed, otherwise
	 * employee not found.
	 * </p>
	 */
	private void removeEmployee() throws EmployeeManagementException {
		boolean canContinue;
		do {
			try {
				if (!(employeeService.removeEmployeeById(getInt(Constants.ENTER_EMPLOYEE_ID_TO_REMOVE)))) {
					throw new EmployeeManagementException(Constants.EMPLOYEE_NOT_FOUND);
				}
				EmployeeManagementLogger.displayInfoLogs(Constants.EMPLOYEE_REMOVED);
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			} finally {
				canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
			}
		} while (canContinue);
	}

	/**
	 * <p>
	 * Get the employee id from the user on which employee need to be updated. If
	 * the employee id exist then update the employee, otherwise display employee
	 * not found.
	 * </p>
	 */
	private void updateEmployee() throws EmployeeManagementException {
		boolean canContinue;
		int employeeId;
		do {
			try {
				employeeId = getInt(Constants.ENTER_ID_TO_UPDATE);
				if (!(employeeService.isIdExist(employeeId))) {
					throw new EmployeeManagementException(Constants.EMPLOYEE_NOT_FOUND);
				}
				// updateEmployeeById(employeeId);
			} catch (EmployeeManagementException employeeManagementException) {
				EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
			} finally {
				canContinue = isChoiceOne(Constants.ENTER_TO_CONTINUE);
			}
		} while (canContinue);
	}

	/**
	 * <p>
	 * Get the choice and input from the user on which employee detail need to be
	 * updated and display the updated employee.
	 * </p>
	 *
	 * @param id an employee id to be updated
	 */
//    private void updateEmployeeById(int employeeId) {
//	Date dateOfBirth = null;
//	Employee employee = null;
//	int choice = 0;
//	do {
//	    try {
//		choice = getInt(Constants.UPDATE_OPTIONS);
//		switch (choice) {
//		case 1:
//		    String name = getValidName(Constants.ENTER_NAME);
//		    employee = employeeService.updateEmployeeById(choice, employeeId, name);
//		    break;
//		case 2:
//		    String address = getValidAddress(Constants.ENTER_ADDRESS);
//		    employee = employeeService.updateEmployeeById(choice, employeeId, address);
//		    break;
//		case 3:
//		    BloodGroup bloodGroup = getValidBloodGroup();
//		    String value = bloodGroup.toString();
//		    employee = employeeService.updateEmployeeById(choice, employeeId, value);
//		    break;
//		case 4:
//		    dateOfBirth = getValidDateOfBirth(Constants.ENTER_DATEOFBIRTH);
//		    String dateOne = DateUtil.formatDate(dateOfBirth);
//		    employee = employeeService.updateEmployeeById(choice, employeeId, dateOne);
//		    break;
//		case 5:
//		    boolean hasExperience = isChoiceOne(Constants.ENTER_EXPERIENCE);
//		    String experience = Boolean.toString(hasExperience);
//		    employee = employeeService.updateEmployeeById(choice, employeeId, experience);
//		    break;
//		case 6:
//		    Date dateOfJoin = getValidDateOfJoining(Constants.ENTER_DATEOFBIRTH, dateOfBirth);
//		    String dateTwo = DateUtil.formatDate(dateOfJoin);
//		    employee = employeeService.updateEmployeeById(choice, employeeId, dateTwo);
//		    break;
//		case 7:
//		    String previousOrganisationName = getValidOrganisationName(Constants.ENTER_ORGANISATION_NAME);
//		    employee = employeeService.updateEmployeeById(choice, employeeId, previousOrganisationName);
//		    break;
//		case 8:
//		    System.out.println(Constants.EXIT_MESSAGE);
//		    break;
//		default:
//		    System.out.println(Constants.INVALID_OPTION);
//		    break;
//		}
//	    } catch (EmployeeManagementException employeeManagementException) {
//		EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
//	    }
//	} while (8 != choice);
//	EmployeeManagementLogger.displayErrorLogs(Constants.EMPLOYEE_UPDATED + employee);
//    }
}
