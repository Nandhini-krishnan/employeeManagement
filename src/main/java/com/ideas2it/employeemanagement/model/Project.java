package com.ideas2it.employeemanagement.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
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
}