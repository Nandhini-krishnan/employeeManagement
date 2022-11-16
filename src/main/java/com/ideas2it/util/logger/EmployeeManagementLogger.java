package com.ideas2it.util.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *<p>
 * EmployeeManagementLogger has the methods to display the logs.
 *</p>
 *
 * @author Naganandhini
 * @version 1.0 05-Oct-2022
 */
public class EmployeeManagementLogger extends Exception{
    static final Logger logger = LogManager.getLogger(EmployeeManagementLogger.class);

    /**
     * <p>
     * To display the info level logs.
     * </p>       
     *
     * @param message - the detailed information
     */
    public static void displayInfoLogs(String message) { 
        logger.info(message);
    }

    /**
     * <p>
     * To display the error level logs.
     * </p>       
     *
     * @param message - the detailed reason of the error
     */
    public static void displayErrorLogs(String message) { 
        logger.error(message);
    }

     /**
     * <p>
     * To display the error level logs.
     * </p>       
     *
     * @param message - the detailed reason of the error
     * @param cause - the cause of the exception
     */
    public static void displayErrorLogs(String message, Throwable cause) { 
        logger.error(message, cause);
    }
}