package com.ideas2it.util.exception;

/*
 * <p>
 * Handles the Custom exception
 * </p>
 *
 * @author Naganandhini
 * @version 1.0 03-SEP-2022
 */
public class EmployeeManagementException extends Exception {
    public EmployeeManagementException(String message) {
            super(message);
    }

    public EmployeeManagementException(String message, Throwable cause) {
            super(message, cause);
    }
}