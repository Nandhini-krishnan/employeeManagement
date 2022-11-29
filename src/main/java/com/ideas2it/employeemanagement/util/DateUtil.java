package com.ideas2it.employeemanagement.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;

import com.ideas2it.employeemanagement.util.Constants;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

/**
 * DateUtil class handles the date related functions 
 */
public class DateUtil {
    private static SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);

    /**
     * To get the parsed date. 
     *
     * @param date - a date to be parsed.
     * @return     - parsed date
     * @throws CustomException - when the given date is not in dd-MM-yyyy format
     */
    public static java.util.Date getParsedDate(String date) throws EmployeeManagementException {
        formatter.setLenient(false);
        try {
            java.util.Date parsedDate = formatter.parse(date);   
            return parsedDate;
        } catch (ParseException parseException) {
            throw new EmployeeManagementException(Constants.DATE_RANGE_EXCEED_ERROR, "400", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * To check the age is valid or not. 
     *
     * @param date - a date
     * @return     - true if the age is in between 18 and 80
     * @throws CustomException - when the age is not in between 18 and 80.
     */
    public static boolean isValidAge(java.util.Date date) throws EmployeeManagementException {
        int age =  differenceBetweenTwoDates(date, getCurrentDate());
        if (!(age > 18 && age <= 80)) {
            throw new EmployeeManagementException(Constants.EMPLOYEE_AGE_ERROR, "400", HttpStatus.BAD_REQUEST);
        }  
        return true;
    }

    /**
     * To get the date in "dd-MM-yyyy" format. 
     *
     * @param date - a date to be formatted
     * @return     - a formatted date
     * @throws CustomException - when the given date is null.
     */
    public static String formatDate(java.util.Date date) throws EmployeeManagementException {
        if (null == date) {
            throw new EmployeeManagementException(Constants.INPUT_NULL_ERROR, "400", HttpStatus.BAD_REQUEST);
        }
        return formatter.format(date);
    }

    public static java.util.Date getCurrentDate() {
        return new java.util.Date();
    }

    public static boolean compareTwoDates(java.util.Date dateOne, java.util.Date dateTwo) throws EmployeeManagementException {
        if (!(dateOne.compareTo(dateTwo) > 0)) {
            throw new EmployeeManagementException(Constants.DATE_OF_JOINING_ERROR, "400", HttpStatus.BAD_REQUEST);
        }
        return true;
    }    

    /**
     * <p>
     * Gets dd-MM-yyyy format date and gives the difference in years from given
     * date to current date. 
     * </p>
     *
     * Ex: date = 29/05/1999, return 23
     * Ex: date = 29/05/2021, return 1
     *
     * @param dateOne - to calculate years 
     * @param dateTwo - to calculate years      
     * @return     - calculated years between given date and current date.   
     * @throws CustomException - when the given dates are null.        
     */
    public static int  differenceBetweenTwoDates(java.util.Date dateOne, java.util.Date dateTwo) throws EmployeeManagementException {
        long yearsDifference;
        if (null == dateOne || null == dateTwo) {
            throw new EmployeeManagementException(Constants.INPUT_NULL_ERROR, "400");
        } else {
            long timeDifference = dateTwo.getTime() - dateOne.getTime();
            yearsDifference = (timeDifference / Constants.TIME_TO_YEAR_CONVERSION);   
        }
        return (int)yearsDifference;
    }     
} 
   