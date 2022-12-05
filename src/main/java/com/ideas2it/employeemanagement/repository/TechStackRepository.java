package com.ideas2it.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2it.employeemanagement.model.TechStack;

/**
 * <p>
 * TechStackRepository interface has the methods to handle techStack's operations
 * </p>
 *
 * @author Naganandhini
 *
 * @version 1.0 30-AUG-2022
 *
 */
public interface TechStackRepository extends JpaRepository<TechStack, Integer>{

}
