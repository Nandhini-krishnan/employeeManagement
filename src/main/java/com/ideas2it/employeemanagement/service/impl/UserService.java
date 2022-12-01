package com.ideas2it.employeemanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ideas2it.employeemanagement.model.User;
import com.ideas2it.employeemanagement.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException("Username Not Found");
		}
		return user;
	}
	
	public User registerUser(User user) {		
		return userRepository.save(user);	
	}
}
