package com.ideas2it.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2it.employeemanagement.model.User;

/**
 * <p>
 * UserRepository interface has the methods to handle user's operations
 * </p>
 *
 * @author Naganandhini
 *
 * @version 1.0 30-AUG-2022
 *
 */
public interface UserRepository extends JpaRepository<User,Integer>{
    
	/**
     * <p>
     * To fetch the users by their name
     * </p>
     *
     * @param username - the username for which employee will be filtered
     * @return        - the list of filtered employees
     */
	User findByUsername(String username);
}
