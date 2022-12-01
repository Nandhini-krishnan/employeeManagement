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

@RestControllerAdvice
public class EmployeeManagementExceptionHandler {
	Map<String,String> errorResponse = new HashMap<>();

	@ExceptionHandler(EmployeeManagementException.class)
	public ResponseEntity<Map<String,String>> handleEmployeeManagementException(EmployeeManagementException ex) {
		errorResponse.clear();
		errorResponse.put("message", ex.getMessage());
		errorResponse.put("code", ex.getCode());
		errorResponse.put("status", ex.getStatus().toString());
		return new ResponseEntity<> (errorResponse, ex.getStatus());		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {	
		errorResponse.clear();
		ex.getBindingResult().getFieldErrors().forEach(error -> errorResponse.put(error.getField(), error.getDefaultMessage()));
		return errorResponse;
		
	}
}