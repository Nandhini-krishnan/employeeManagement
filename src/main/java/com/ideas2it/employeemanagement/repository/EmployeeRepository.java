package com.ideas2it.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.employeemanagement.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
		
}
