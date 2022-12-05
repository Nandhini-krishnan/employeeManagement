package com.ideas2it.employeemanagement.util.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * Handles the exception thrown by Employee Management Application
 * </p>
 *
 * @author Naganandhini
 * @version 1.0 03-SEP-2022
 */
@RestControllerAdvice
public class EmployeeManagementExceptionHandler {
	Map<String,String> errorResponse = new HashMap<>();

	/**
     * <p>
     * To handle the EmployeeManagementException.
     * </p>
     *
     * @param exception - an exception to be handled
     * @return - the error message with respective status
     */
	@ExceptionHandler(EmployeeManagementException.class)
	public ResponseEntity<Map<String,String>> handleEmployeeManagementException(EmployeeManagementException exception) {
		errorResponse.clear();
		errorResponse.put("message", exception.getMessage());
		errorResponse.put("code", exception.getCode());
		errorResponse.put("status", exception.getStatus().toString());
		return new ResponseEntity<> (errorResponse, exception.getStatus());		
	}
	
	/**
     * <p>
     * To handle the MethodArgumentNotValidException.
     * </p>
     *
     * @param exception - an exception to be handled
     * @return - the error message with respective status
     */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {	
		errorResponse.clear();
		exception.getBindingResult().getFieldErrors().forEach(error -> errorResponse.put(error.getField(), error.getDefaultMessage()));
		return errorResponse;
		
	}
}