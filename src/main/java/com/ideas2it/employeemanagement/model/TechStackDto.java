package com.ideas2it.employeemanagement.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


/**
 * <h1>Project</h1>
 */
@Component
public class TechStackDto {

	private String name;
	private float version;
	List<ProjectDto> projects;

	public TechStackDto() {
	}

	public TechStackDto(String name, float version) {
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

	public void setProjects(List<ProjectDto> projects) {
		this.projects = projects;
	}

	public List<ProjectDto> getProjects() {
		return projects;
	}

	@Override
	public String toString() {
		StringBuilder techStack = new StringBuilder();
		techStack.append("\nTech Stack name: ");
		techStack.append(name);
		techStack.append("\nVersion: ");
		techStack.append(version);
		techStack.append("\nProjects: ");
		if (null != projects && !projects.isEmpty()) {
			techStack.append(projects.stream().map(project -> project.getName()).collect(Collectors.joining(",")));
		} else {
			techStack.append(" No projects assigned");
		}
		return techStack.toString();

	}
}
