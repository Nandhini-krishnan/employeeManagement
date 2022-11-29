package com.ideas2it.employeemanagement.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.ideas2it.employeemanagement.util.DateUtil;
import com.ideas2it.employeemanagement.util.enumeration.BloodGroup;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;

@Component
public class EmployeeDto {
   	
	private int id;
    
	private String employeeCode;
	
	@NotNull(message = "Employee name should not be null or empty")
	@Pattern(regexp = "[^\\s][[^0-9]a-zA-Z\\s]*", message = "only alphabets and space are allowed")
	private String name;

	@NotNull(message = "Employee name should not be null or empty")
	@Pattern(regexp = "[^\\s][\\w\\-,/\\s\\w\\-,/]*", message = "only alphanumeric,space,special character(- , /) are allowed")
	@Size(min=5, max=25, message = "Please enter the addressess between 5 and 255 characters")
	private String address;

	@NotNull(message = "Blood Group should not be null or empty")
	private BloodGroup bloodGroup;

	@NotNull(message = "Date of Birth should not be null or empty")
	private Date dateOfBirth;

	@NotNull(message = "Experience should not be null or empty")
	private boolean experience;

	@NotNull(message = "Date of Join should not be null or empty")
	private Date dateOfJoin;

	@Pattern(regexp = "[^\\s][\\w\\s\\w]*$", message = "only alphanumeric and sapce are allowed")
	private String previousOrganisationName;

	private List<ProjectDto> projects;

	public EmployeeDto() {
	}

	public EmployeeDto(String name, String address, BloodGroup bloodGroup, Date dateOfBirth, boolean experience,
			Date dateOfJoin, String previousOrganisationName) {
		this.name = name;
		this.address = address;
		this.bloodGroup = bloodGroup;
		this.dateOfBirth = dateOfBirth;
		this.experience = experience;
		this.dateOfJoin = dateOfJoin;
		this.previousOrganisationName = previousOrganisationName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
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

	public void setProjects(List<ProjectDto> projects) {
		this.projects = projects;
	}

	public List<ProjectDto> getProjects() {
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
		employee.append(experience);
		employee.append("\nPrevious Organisation Name");
		employee.append(previousOrganisationName);
		employee.append("\nEmployee Age: ");
		//employee.append(getAge());
		employee.append("\nProjects: ");
		if (null != projects && !projects.isEmpty()) {
			employee.append(projects.stream().map(project -> project.getName()).collect(Collectors.joining(",")));
		} else {
			employee.append(" No projects assigned");
		}
		return employee.toString();
	}
}

