package com.ideas2it.employeemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.employeemanagement.model.User;
import com.ideas2it.employeemanagement.service.impl.UserService;

@RestController
public class UserController {
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {	
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userService.registerUser(user);	
	}
		
}
