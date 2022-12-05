package com.ideas2it.employeemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.employeemanagement.model.User;
import com.ideas2it.employeemanagement.model.UserRequest;
import com.ideas2it.employeemanagement.service.impl.UserDetailsServiceImpl;
import com.ideas2it.employeemanagement.util.jwtutil.JwtUtil;

/**
 * <p>
 * Gets input for the users and then return the results of project's
 * operations.
 * </p>
 *
 * @author Naganandhini version 1.0 28-NOV-2022
 */
@RestController
public class UserController {
    
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {	
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDetailsService.registerUser(user);	
	}
	
	@PostMapping("/loginUser")
	public String login(@RequestBody UserRequest user) {
		return JwtUtil.generateToken(user.getUsername());
	}
		
}
