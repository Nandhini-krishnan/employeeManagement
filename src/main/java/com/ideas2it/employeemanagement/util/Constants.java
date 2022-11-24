package com.ideas2it.employeemanagement.util;

/**
 * Handles the constant related to employee
 */
public class Constants {
    public static final String EMPLOYEE_OPTIONS = "Choose:\n1.Create Employee"
	    + "\n2.Assign Projects"
	    + "\n3.Show Employees"
	    + "\n4.Search Employees"
	    + "\n5.Show Employees By Experience"
	    + "\n6.Show Employees By Date Of Join"
	    + "\n7.Show Employees By Project Id"
	    + "\n8.Show Employees By Multiple Id"
	    + "\n9.Show Employee By Id"
	    + "\n10.Delete Employee"
	    + "\n11.Update Employee"
	    + "\n12.Exit";
    public static final String UPDATE_OPTIONS = "Choose the details you want to update:"
	    + "\n1.Name \n2.Address \n3.Bloodgroup"
	    + "\n4.Date of birth \n5.Experience"
	    + "\n6.4.Date of joining"
	    + "\n7.Previoue organisation name"
	    + "\n8.Exit";
    public static final String BLOODGROUP_OPTIONS = "Choose the blood group:"
	    + "\n1.A+ve \n2.A-ve \n3.B+ve"
	    + "\n4.B-ve \n5.O+ve \n6.O-ve \n7.AB+ve \n8.AB-ve"
	    + "\n9.A1+ve \n10.A1-ve \n11.A1B+ve \n12.A1B-ve"
	    + "\n13.A2B+ve \n14.A2B-ve \n15.others";
    public static final String PROJECT_OPTIONS = "Choose:\n1.Create Project"
	    + "\n2.Assign Tech Stack"
	    + "\n3.Show Projects"
	    + "\n4.Show Project By Id"
	    + "\n5.Show Projects By Employee Id"
	    + "\n6.Update Project"
	    + "\n7.Delete Project"
	    + "\n8.Exit";
    public static final String TECH_STACK_OPTIONS = "Choose:\n1.Create Tech Stack"
	    + "\n2.Show Tech Stacks"
	    + "\n3.Show Tech Stack By Id"
	    + "\n4.Show Tech Stacks By Project Id"
	    + "\n5.Delete Tech Stack"
	    + "\n6.Update Tech Stack"
	    + "\n7.Exit";
    public static final String EMPLOYEE_MANAGEMENT_OPTIONS = "Choose: \n1.Employee \n2.Project "
	    + "\n3.Tech Stack \n4.Exit";
    public static final String INVALID_OPTION = "Invalid Option \nPlease Choose the valid option";
    public static final String EXIT_MESSAGE = "You are exited";
    public static final String ENTER_NAME = "Enter the name";
    public static final String ENTER_ADDRESS= "Enter the address";
    public static final String ENTER_ORGANISATION_NAME = "Enter the previous organisation name";
    public static final String ENTER_DOMAIN_NAME = "Enter the domain name";
    public static final String ENTER_VALID_DOMAIN_NAME = "Enter the valid domain name";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final Long TIME_TO_YEAR_CONVERSION = (1000l * 60 * 60 * 24 * 365);
    public static final String ENTER_DATEOFBIRTH = "Enter the date of birth(" + DATE_FORMAT + ")";
    public static final String ENTER_DATEOFJOINING = "Enter the date of joining(" + DATE_FORMAT + ")";
    public static final String ENTER_EXPERIENCE = "\nDo you have work Experience \n yes(press 1) \n no(press any other number)";
    public static final String EMPLOYEE_CREATED = "Employee is created\n";
    public static final String EMPLOYEE_UPDATED = "Employee is updated\n";
    public static final String EMPLOYEE_REMOVED = "Employee is removed";
    public static final String NO_RECORD_FOUND = "No record found";
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
    public static final String PROJECT_NOT_FOUND = "Project not found";
    public static final String ENTER_NAME_TO_SEARCH = "Enter the employee name you want to search";
    public static final String ENTER_ID_TO_SEARCH = "Enter the employee id you want to search";
    public static final String ENTER_EMPLOYEE_ID_TO_REMOVE = "Enter the employee id you want to remove";
    public static final String ENTER_PROJECT_ID_TO_REMOVE = "Enter the project id you want to remove";
    public static final String ENTER_ID_TO_UPDATE = "Enter the employee id you want to update";
    public static final String DATE_RANGE_EXCEED_ERROR = "You have entered the date which exceeds range";
    public static final String DATE_FORMAT_ERROR = "You have entered the input in wrong format(Example for correct format: 01-01-1990)";
    public static final String ADDRESS_FORMAT_ERROR = "You have entered the input in wrong format(only allows alphanumeric and /-, characters)";
    public static final String NAME_FORMAT_ERROR = "You have entered the input in wrong format(only allows alphanumeric and space)";
    public static final String EMPLOYEE_AGE_ERROR = "Employee's age must be in between 18 and 80";
    public static final String INPUT_NULL_ERROR = "Something went wrong!Input should not be null";
    public static final String ENTER_TO_CONTINUE = "Do you want to continue \nyes(press 1) \nno(press any other number)";
    public static final String ENTER_EMPLOYEE_EXPERIENCE_TO_SEARCH = "Enter the employee's experience in years you want to search";
    public static final String DATE_OF_JOINING_ERROR = "Date of joining must be greater than the date of birth";
    public static final String ENTER_PROJECT_NAME = "Enter the project name";
    public static final String ENTER_TECHNOLOGY_NAME = "Enter the technology name";
    public static final String ENTER_VERSION = "Enter the version of technology";
    public static final String ENTER_PROJECT_ID = "Enter the project id";
    public static final String ENTER_TECHNOLOGY = "Enter the project technology";
    public static final String ENTER_START_DATE = "Enter the start date of project";
    public static final String ENTER_END_DATE = "Enter the end date of project";
    public static final String PROJECT_CREATED = "Project is created";
    public static final String PROJECT_REMOVED = "Project is removed";
    public static final String ENTER_FIRST_DATE_TO_SEARCH = "Enter the first date of join to search";
    public static final String ENTER_SECOND_DATE_TO_SEARCH = "Enter the second date of join to search";
    public static final String ENTER_EMPLOYEE_ID_TO_ASSIGN_PROJECT = "Enter the employee id to assign the project";
    public static final String ENTER_TECH_STACK_ID = "Enter the tech stack id";
    public static final String TECH_STACK_REMOVED = "Tech stack is removed successfully";
    public static final String TECH_STACK_NOT_FOUND = "Tech Stack not found";
    public static final String ENTER_EMPLOYEE_ID = "Enter the employee id";
    public static final String ENTER_NUMBER_OF_ID = "Enter the number of id you want to search";
    public static final String TECH_STACK_UPDATED = "Tech stack is updated\n";
    public static final String PROJECT_UPDATED = "Project is updated\n";
    public static final String INSERTION_UNSUCCESSFUL = "Insertion is unsuccessful\n";
    public static final String INSERTION_SUCCESSFUL = "Insertion is successful\n";
}
