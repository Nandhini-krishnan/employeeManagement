package com.ideas2it.employeemanagement.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	/**
     * <p>
     * Generate the employee id as per the count of the employee
     * </p>
     *
     * @return an employee id with prefix as EMP
     */
	@Query(value = "select count(id) from employees" , nativeQuery = true)
	Long getEmployeesCount();
    
	/**
     * <p>
     * To fetch the employees between the range of date.
     * </p>
     *
     * @param startDate - a start date for which employee will be filtered
     * @param endDate - an end date for which employee will be filtered
     * @return        - the list of filtered employees
     */
	List<Employee> findByDateOfJoinBetween(Date startDate, Date endDate);
      
	/**
     * <p>
     * To fetch the employees for the multiple given id.
     * </p>
     *
     * @param listOfId - the list of id for which the employee to be returned
     * @return   - the list of filtered employees
     */
	List<Employee> findByIdIn(List<Integer> listOfId);
    
	/**
     * <p>
     * To search the employees.
     * </p>
     *
     * @param keyword - an input for which employee will be filtered
     * @return - the list of filtered employees
     */
    @Query("select e from Employee e where e.name like %:keyword% or e.address like %:keyword% or e.previousOrganisationName like %:keyword% or e.bloodGroup like %:keyword%")
    List<Employee> searchEmployees(@Param("keyword") String keyword);
}

