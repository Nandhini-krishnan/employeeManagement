package com.ideas2it.employeemanagement.util.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeManagementExceptionHandler {
	Map<String,String> error = new HashMap<>();
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EmployeeManagementException.class)
	public Map<String,String> handleEmployeeManagementException(EmployeeManagementException ex) {
	    error.put("message", ex.getMessage());
		return error;		
	}

}
