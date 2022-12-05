package com.ideas2it.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.employeemanagement.model.Project;

/**
 * <p>
 * ProjectRepository interface has the methods to handle project's operations
 * </p>
 *
 * @author Naganandhini
 *
 * @version 1.0 30-AUG-2022
 *
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
