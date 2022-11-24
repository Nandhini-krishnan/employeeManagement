package com.ideas2it.employeemanagement.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <p>
 * Project class has the getters and setters for project details.
 * </p>
 *
 * @author  Naganandhini
 * @version  1.0  09-AUG-2022
 */

@Component
@Entity
@Table(name = "projects")
@SQLDelete(sql = "update projects set is_deleted = 1 where id =?")
@Where(clause = "is_deleted = false")
public class Project extends BaseModel {
    @Column(nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @ManyToMany(mappedBy = "projects", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<Employee> employees;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
	    name = "projects_tech_stacks",
	    joinColumns = @JoinColumn(name = "project_id"),
	    inverseJoinColumns = @JoinColumn(name = "tech_stack_id")
	    )

    private List<TechStack> techStacks;

    public Project() {}

    public Project(
	    String name,
	    Date startDate,
	    Date endDate
	    ) {
	this.name = name;
	this.startDate = startDate;
	this.endDate = endDate;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    public Date getStartDate() {
	return endDate;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    public Date getEndDate() {
	return endDate;
    }

    public void setEmployees(List<Employee> employees) {
	this.employees = employees;
    }

    public List<Employee> getEmployees() {
	return employees;
    }

    public void setTechStacks(List<TechStack> techStacks) {
	this.techStacks = techStacks;
    }

    public List<TechStack> getTechStacks() {
	return techStacks;
    }

    /**
     * <p>
     * To display the project.
     * </p>
     */
    @Override
    public String toString() {
	StringBuilder project = new StringBuilder();
	project.append("\nProject Id: ");
	project.append(getId());
	project.append("\nProject name: ");
	project.append(name);
	project.append("\nProject Start Date : ");
	project.append(startDate);
	project.append("\nProject End Date: ");
	project.append(endDate);
	project.append("\nDelete Status: ");
	project.append(isDeleted());
	project.append("\nLast Created Date and Time: ");
	project.append(getCreatedAt());
	project.append("\nLast Updated Date and Time: ");
	project.append(getUpdatedAt());
	project.append("\nEmployees: ");
	if (null != employees && !employees.isEmpty()) {
	    project.append(employees.stream().map(employee -> employee.getName()).collect(Collectors.joining(",")));
	} else {
	    project.append(" No employees assigned");
	}
	project.append("\nTech Stacks: ");
	if (null != techStacks && !techStacks.isEmpty()) {
	    project.append(techStacks.stream().map(employee -> employee.getName()).collect(Collectors.joining(",")));
	} else {
	    project.append(" No tech stacks assigned");
	}
	return project.toString();
    }
}