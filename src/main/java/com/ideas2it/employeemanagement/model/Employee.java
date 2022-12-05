package com.ideas2it.employeemanagement.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ideas2it.employeemanagement.util.enumeration.BloodGroup;

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
@SQLDelete(sql = "update employees set is_deleted = 1 where id =?")
@Where(clause = "is_deleted = false")
public class Employee extends BaseModel {

	@Column(nullable = false)
	private String name;

	@Column(name = "employee_code", nullable = false, unique = true)
	private String employeeCode;

	@Column(nullable = false)
	private String address;

	@Enumerated(EnumType.STRING)
	@Column(name = "blood_group", nullable = false)
	private BloodGroup bloodGroup;

	@Column(name = "date_of_birth", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(name = "is_experienced", columnDefinition = "tinyint(1) default false", nullable = false)
	private boolean experience;

	@Column(name = "date_of_join", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateOfJoin;

	@Column(name = "previous_organisation_name", nullable = true)
	private String previousOrganisationName;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "employees_projects", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<Project> projects;

	public Employee() {
	}

	public Employee(String name, String address, BloodGroup bloodGroup, Date dateOfBirth, boolean experience,
			Date dateOfJoin, String previousOrganisationName) {
		this.name = name;
		this.address = address;
		this.bloodGroup = bloodGroup;
		this.dateOfBirth = dateOfBirth;
		this.experience = experience;
		this.dateOfJoin = dateOfJoin;
		this.previousOrganisationName = previousOrganisationName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeCode() {
		return employeeCode;
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

	public void setExperience(boolean experience) {
		this.experience = experience;
	}

	public boolean isExperience() {
		return experience;
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
		return projects;
	}
}
