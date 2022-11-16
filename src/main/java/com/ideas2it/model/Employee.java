package com.ideas2it.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ideas2it.util.DateUtil;
import com.ideas2it.util.enumeration.BloodGroup;
import com.ideas2it.util.exception.EmployeeManagementException;

/**
 * <p>
 * Employee class has the getters and setters for employee details.
 * </p>
 *
 * @author Naganandhini
 * @version 1.0 09-AUG-2022
 */
@Entity
@Table(name = "employees")
public class Employee extends BaseModel {
    @Column(name = "employee_code", nullable = false, unique = true)
    private String employeeCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group", nullable = false)
    private BloodGroup bloodGroup;

    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "is_experienced", nullable = false)
    private boolean isExperienced;

    @Column(name = "date_of_join", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfJoin;

    @Column(name = "previous_organisation_name", nullable = true)
    private String previousOrganisationName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Employees_projects", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects;

    public Employee() {
    }

    public Employee(String employeeCode, String name, String address, BloodGroup bloodGroup, Date dateOfBirth,
	    boolean isExperienced, Date dateOfJoin, String previousOrganisationName) {
	this.employeeCode = employeeCode;
	this.name = name;
	this.address = address;
	this.bloodGroup = bloodGroup;
	this.dateOfBirth = dateOfBirth;
	this.isExperienced = isExperienced;
	this.dateOfJoin = dateOfJoin;
	this.previousOrganisationName = previousOrganisationName;
    }

    public void setEmployeeCode(String employeeCode) {
	this.employeeCode = employeeCode;
    }

    public String getEmployeeCode() {
	return employeeCode;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getAddress() {
	return address;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
	this.bloodGroup = bloodGroup;
    }

    public BloodGroup getBloodGroup() {
	return bloodGroup;
    }

    public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth() {
	return dateOfBirth;
    }

    public void setDateOfJoin(Date dateOfJoin) {
	this.dateOfJoin = dateOfJoin;
    }

    public Date getDateOfJoin() {
	return dateOfJoin;
    }

    public void setExperience(boolean isExperienced) {
	this.isExperienced = isExperienced;
    }

    public boolean hasExperience() {
	return isExperienced;
    }

    public void setPreviousOrganisationName(String previousOrganisationName) {
	this.previousOrganisationName = previousOrganisationName;
    }

    public String getPreviousOrganisationName() {
	return previousOrganisationName;
    }

    public void setProjects(List<Project> projects) {
	this.projects = projects;
    }

    public List<Project> getProjects() {
	if (null == projects) {
	    projects = new ArrayList<>();
	}
	return projects;
    }

    /**
     * <p>
     * To get the age of employee.
     * </p>
     *
     * @return - the employee age
     */
    public int getAge() {
	int age = 0;
	try {
	    age = DateUtil.differenceBetweenTwoDates(dateOfBirth, DateUtil.getCurrentDate());
	} catch (EmployeeManagementException employeeManagementException) {
	    System.out.println(employeeManagementException);
	}
	return age;
    }

    /**
     * <p>
     * To display the employee.
     * </p>
     */
    @Override
    public String toString() {
	StringBuilder employee = new StringBuilder();
	employee.append("\nEmployee Id: ");
	employee.append(getId());
	employee.append("\nEmployee Code: ");
	employee.append(employeeCode);
	employee.append("\nEmployee name: ");
	employee.append(name);
	employee.append("\nEmployee address: ");
	employee.append(address);
	employee.append("\nBlood Group: ");
	employee.append(bloodGroup);
	employee.append("\nDate Of Birth: ");
	employee.append(dateOfBirth);
	employee.append("\nDate Of Join: ");
	employee.append(dateOfJoin);
	employee.append("\nExperience Status: ");
	employee.append(isExperienced);
	employee.append("\nPrevious Organisation Name");
	employee.append(previousOrganisationName);
	employee.append("\nEmployee Age: ");
	employee.append(getAge());
	employee.append("\nDelete Status: ");
	employee.append(isDeleted());
	employee.append("\nLast Created Date and Time: ");
	employee.append(getCreatedAt());
	employee.append("\nLast Updated Date and Time: ");
	employee.append(getUpdatedAt());
	employee.append("\nProjects: ");
	if (null != projects && !projects.isEmpty()) {
	    employee.append(projects.stream().map(project -> project.getName()).collect(Collectors.joining(",")));
	} else {
	    employee.append(" No projects assigned");
	}
	return employee.toString();
    }
}
