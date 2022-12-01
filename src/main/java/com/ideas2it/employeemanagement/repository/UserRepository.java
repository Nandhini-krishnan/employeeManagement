package com.ideas2it.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2it.employeemanagement.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
      
	User findByUsername(String username);
}
