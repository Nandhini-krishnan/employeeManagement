package com.ideas2it.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.employeemanagement.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
