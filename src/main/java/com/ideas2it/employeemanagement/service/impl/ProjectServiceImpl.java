package com.ideas2it.employeemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ideas2it.employeemanagement.dto.ProjectDto;
import com.ideas2it.employeemanagement.dto.TechStackDto;
import com.ideas2it.employeemanagement.repository.ProjectRepository;
import com.ideas2it.employeemanagement.service.ProjectService;
import com.ideas2it.employeemanagement.util.Constants;
import com.ideas2it.employeemanagement.util.DateUtil;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.util.mapper.ProjectMapper;

/**
 * <p>
 * ProjectServiceImpl has the methods implementations of ProjectService to
 * handle project's operations.
 * </p>
 *
 * @author Naganandhini
 * @version 1.0 10-Aug-2022
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	/**
	 * {@inheritDoc}
	 * @throws EmployeeManagementException 
	 */
	@Override
	public ProjectDto createProject(ProjectDto projectDto) throws EmployeeManagementException {
		ProjectDto createdProject = null;
		if (DateUtil.compareTwoDates(projectDto.getStartDate(), projectDto.getEndDate())) {
			createdProject = ProjectMapper.convertIntoDto(projectRepository.save(ProjectMapper.convertIntoEntity(projectDto)));
		}
		return createdProject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProjectDto> getProjects() throws EmployeeManagementException {
		List<ProjectDto> projects = ProjectMapper.convertIntoProjectsDto(projectRepository.findAll());
		if (projects.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND, "404", HttpStatus.NOT_FOUND);
		}
		return projects;
	}

	/**
	 * {@inheritDoc}
	 */
	public ProjectDto getProjectById(int id) throws EmployeeManagementException {
		return ProjectMapper.convertIntoDto(projectRepository.findById(id).orElseThrow(
				() -> new EmployeeManagementException(Constants.PROJECT_NOT_FOUND, "404", HttpStatus.NOT_FOUND)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String deleteProjectById(int id) throws EmployeeManagementException {
		if (projectRepository.existsById(id)) {
			throw new EmployeeManagementException(Constants.PROJECT_NOT_FOUND, "404", HttpStatus.NOT_FOUND);	
		} 
		projectRepository.deleteById(id);
		return "deleted successfully " + id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String updateProject(ProjectDto projectDto, int id) throws EmployeeManagementException {
		String message = null;
		if (projectRepository.existsById(id)) {
			ProjectDto existingProject = getProjectById(id);
			if (null != existingProject) {
				existingProject.setName(projectDto.getName());
				existingProject.setStartDate(projectDto.getStartDate());
				existingProject.setEndDate(projectDto.getEndDate());
				if (null != projectDto.getTechStacks()) {
					List<TechStackDto> input = existingProject.getTechStacks();
					input.addAll(projectDto.getTechStacks());
					existingProject.setTechStacks(input);
				}
				if (existingProject.equals(projectRepository.save(ProjectMapper.convertIntoEntity(projectDto)))) {
					message = projectDto.getName() + " Update Successfully";
				} else {
					message = projectDto.getName() + " Update UnSuccessfull";
				}
			}
		} else {
			throw new EmployeeManagementException(Constants.PROJECT_NOT_FOUND, "404", HttpStatus.NOT_FOUND);
		}
		return message;
	}

}
