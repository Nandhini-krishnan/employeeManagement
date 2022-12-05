package com.ideas2it.employeemanagement.util.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ideas2it.employeemanagement.dto.ProjectDto;
import com.ideas2it.employeemanagement.dto.TechStackDto;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.TechStack;

/**
 * <p>
 * Handles the converting process from entity to DTO for techStack.
 * </p
 *
 * @author Naganandhini
 * @version 1.0 25-NOV-2022
 */
public class TechStackMapper {
	
	/**
     * <p>
     * To convert from entity to DTO for techStack
     * </p>
     *
     * @param techStack - an techStack entity to be converted into DTO.
     * @return   - the techStack DTO.
     */
	public static TechStackDto convertIntoDto(TechStack techStack) {
		List<Project> projects;
		TechStackDto techStackDto = null;
		List<ProjectDto> projectsDto = null;
		if (null != techStack) {
			techStackDto = new TechStackDto();
			techStackDto.setName(techStack.getName());
			techStackDto.setVersion(techStack.getVersion());
			projects = techStack.getProjects();
			if (null != projects) {
				projectsDto = projects.stream().map((Function<Project, ProjectDto>) p -> {
					p.setTechStacks(null);
					return ProjectMapper.convertIntoDto(p);
				}).collect(Collectors.toList());
			}
			techStackDto.setProjects(projectsDto);
		}
		return techStackDto;
	}

	/**
     * <p>
     * To convert from DTO to Entity for techStack
     * </p>
     *
     * @param techStack - an techStack DTO to be converted into entity.
     * @return   - the techStack.
     */
	public static TechStack convertIntoEntity(TechStackDto techStackDto) {
		List<ProjectDto> projectsDto;
		List<Project> projects = null;
		TechStack techStack = null;
		if (null != techStackDto) {
			techStack = new TechStack();
			techStack.setName(techStackDto.getName());
			techStack.setVersion(techStackDto.getVersion());
			projectsDto = techStackDto.getProjects();
			if (null != projectsDto) {
				projects = projectsDto.stream().map((Function<ProjectDto, Project>) p -> {
						p.setTechStacks(null);
						return ProjectMapper.convertIntoEntity(p);
				}).collect(Collectors.toList());
			}
			techStack.setProjects(projects);
		}
		return techStack;
	}

	/**
     * <p>
     * To convert from DTO to Entity for the list of techStacks
     * </p>
     *
     * @param techStacks - the list of techStacks DTO to be converted into entity.
     * @return   - the list of techStacks DTO.
     */
	public static List<TechStackDto> convertIntoTechStacksDto(List<TechStack> techStacks) {
		List<TechStackDto> techStacksDto = null;
		if (null != techStacks) {
			techStacksDto = techStacks.stream().map(t -> convertIntoDto(t)).collect(Collectors.toList());
		}		
		return techStacksDto;	
	}
}
