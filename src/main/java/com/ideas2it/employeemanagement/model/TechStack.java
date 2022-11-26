package com.ideas2it.employeemanagement.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <h1>Project</h1>
 */


@Entity
@Table(name = "tech_stacks")
@SQLDelete(sql = "update tech_stacks set is_deleted = 1 where id =?")
@Where(clause = "is_deleted = false")
public class TechStack extends BaseModel {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private float version;

    @ManyToMany(mappedBy = "techStacks")
    List<Project> projects;

    public TechStack() {}

    public TechStack(
	    String name,
	    float version
	    ) {
	this.name = name;
	this.version = version;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setVersion(float version) {
	this.version = version;
    }

    public float getVersion() {
	return version;
    }

    public void setProjects(List<Project> projects) {
	this.projects = projects;
    }

    public List<Project> getProjects() {
	return projects;
    }

    @Override
    public String toString() {
	StringBuilder techStack = new StringBuilder();
	techStack.append("\nTech Stack Id: ");
	techStack.append(getId());
	techStack.append("\nTech Stack name: ");
	techStack.append(name);
	techStack.append("\nVersion: ");
	techStack.append(version);
	techStack.append("\nDelete Status: ");
	techStack.append(isDeleted());
	techStack.append("\nLast Created Date and Time: ");
	techStack.append(getCreatedAt());
	techStack.append("\nLast Updated Date and Time: ");
	techStack.append(getUpdatedAt());
	techStack.append("\nProjects: ");
	if (null != projects && !projects.isEmpty()) {
	    techStack.append(projects.stream().map(project -> project.getName()).collect(Collectors.joining(",")));
	} else {
	    techStack.append(" No projects assigned");
	}
	return techStack.toString();

    }
}