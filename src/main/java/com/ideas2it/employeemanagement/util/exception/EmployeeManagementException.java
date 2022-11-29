package com.ideas2it.employeemanagement.util.exception;

import org.springframework.http.HttpStatus;

/*
 * <p>
 * Handles the Custom exception
 * </p>
 *
 * @author Naganandhini
 * @version 1.0 03-SEP-2022
 */
public class EmployeeManagementException extends Exception {

	String code;
	HttpStatus status;

	public EmployeeManagementException(String message, String code) {
		super(message);
		this.code = code;
	}

	public EmployeeManagementException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeManagementException(String message) {
		super(message);
	}

	public EmployeeManagementException(String message, String code, HttpStatus status) {
		super(message);
		this.code = code;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}