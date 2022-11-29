package com.ideas2it.employeemanagement.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ideas2it.employeemanagement.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query(value = "select count(id) from employees" , nativeQuery = true)
	Long getEmployeesCount();
     
	List<Employee> findByDateOfJoinBetween(Date startDate, Date endDate);
      
	List<Employee> findByIdIn(List<Integer> listOfId);
      
    @Query("select e from Employee e where e.name like %:keyword% or e.address like %:keyword% or e.previousOrganisationName like %:keyword% or e.bloodGroup like %:keyword%")
    List<Employee> searchEmployees(@Param("keyword") String keyword);
}

