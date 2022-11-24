package com.ideas2it.employeemanagement.util;

import java.text.ParseException;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.util.Constants;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

/**
 * <p>
 * Validation class gets the input from controller and validated the
 * input.
 * </p>       
 */
public class Validation {
    
    /**
     * This method is used to validate the name.
     *
     * @param name - the name to be validated       
     * @return     - true if name matches [^\\s][^0-9][a-zA-Z\\s]* pattern
     * @throws EmployeeManagementException - when the given input does not matches                  
     */
    public static boolean isValidName(String input) throws EmployeeManagementException {
        if(!(Pattern.matches("[^\\s][[^0-9]a-zA-Z\\s]*", input))) {
            throw new EmployeeManagementException(Constants.NAME_FORMAT_ERROR);
        }
        return true;
    }
    
    /**
     * This method is used to validate the address.
     *
     * @param address - an address to be validated       
     * @return        - true if address matches [^\\s][\\w\\s\\w\\-,/]* pattern    
     * @throws EmployeeManagementException - when the given input does not matches      
     */
    public static boolean isValidAddress(String input) throws EmployeeManagementException {
        if(!(Pattern.matches("[^\\s][\\w\\-,/\\s\\w\\-,/]*", input))) {
            throw new EmployeeManagementException(Constants.ADDRESS_FORMAT_ERROR);
        }
        return true;
    }

    /**
     * This method is used to validate the date pattern.
     *
     * @param address - an date to be validated       
     * @return        - true if address matches ^\\d{2}-\\d{2}-\\d{4}$ pattern    
     * @throws EmployeeManagementException - when the given input does not matches     
     */
    public static boolean isValidDate(String input) throws EmployeeManagementException {
        if(!(Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", input))) {
            throw new EmployeeManagementException(Constants.DATE_FORMAT_ERROR);
        }
        return true;
    }

    /**
     * This method is used to validate the organisation name.
     *
     * @param name - an organisation name to be validated       
     * @return     - true if organisation name matches [^\\s][\\w\\s\\w]* pattern                  
     * @throws EmployeeManagementException - when the given input does not matches                     
     */
    public static boolean isValidOrganisationName(String input) throws EmployeeManagementException {
        if(!(Pattern.matches("[^\\s][^0-9][\\w\\s\\w]*$", input))) {
            throw new EmployeeManagementException(Constants.NAME_FORMAT_ERROR);
        }
        return true;
    }

    /**
     * This method is used to validate the version of technology.
     *
     * @param name - a version to be validated       
     * @return     - true if the version matches [^\\s][\\w\\s\\w]* pattern                  
     * @throws EmployeeManagementException - when the given input does not matches                     
     */
    public static boolean isValidVersion(float input) throws EmployeeManagementException {
        String value = String.valueOf(input);
        if(!(Pattern.matches("[0-9\\.]*", value))) {
            throw new EmployeeManagementException(Constants.NAME_FORMAT_ERROR);
        }
        return true;
    }
}    