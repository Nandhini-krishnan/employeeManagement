package com.ideas2it.util.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ideas2it.util.exception.EmployeeManagementException;
import com.ideas2it.util.logger.EmployeeManagementLogger;

/**
 * <p>
 * DatabaseConnection singleton class has the methods to connect database
 * </p>
 *
 * @author Naganandhini
 * version 1.0  10-SEP-2022
 */
public class DatabaseConnection {   
    private static DatabaseConnection databaseConnection;
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost:3306/employee_management"; 
    private static String userName = "root";
    private static String password = "Raji@1998";

    private DatabaseConnection() throws SQLException {
        connection = DriverManager.getConnection(url,userName,password);
    }

    /**
     * <p>
     * This method creates an object for DataBaseConnection
     * </p>
     *
     * @return - the database connection
     */
    public static Connection getConnection() throws SQLException {
        if (null == connection || connection.isClosed()) {
            databaseConnection = new DatabaseConnection();
        }
        return connection;
    } 

    /**
     * <p>
     * To close the connection.
     * </p>
     *
     * @param resultset - to be closed
     * @param preparedStatement - to be closed
     */
    public static void closeConnection(ResultSet resultSet, 
                                       PreparedStatement preparedStatement 
                                      ) throws EmployeeManagementException {
        try {
            if (null != resultSet) {
                resultSet.close();
            }
        } catch (SQLException sqlException) {
            EmployeeManagementLogger.displayErrorLogs(sqlException.getMessage());
        }
        try {
            if (null != preparedStatement) {
                preparedStatement.close();
            }
        } catch (SQLException sqlException) {
            EmployeeManagementLogger.displayErrorLogs(sqlException.getMessage());
        }
        try {
            if(null != connection) {
                connection.close();
            }
        } catch (SQLException sqlException) {
           EmployeeManagementLogger.displayErrorLogs(sqlException.getMessage());
        } 
    }       
}