package com.ideas2it.employeemanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ideas2it.employeemanagement.model.User;
import com.ideas2it.employeemanagement.repository.UserRepository;

/**
 * <p>
 * UserDetailsServiceImpl has the methods implementations of UserDetailsService to
 * handle user's operations.
 * </p>
 *
 * @author Naganandhini
 * @version 1.0 10-Aug-2022
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * {@inheritDoc}
	 */
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

