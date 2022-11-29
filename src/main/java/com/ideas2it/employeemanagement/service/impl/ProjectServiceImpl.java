package com.ideas2it.employeemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ideas2it.employeemanagement.model.ProjectDto;
import com.ideas2it.employeemanagement.model.TechStackDto;
import com.ideas2it.employeemanagement.repository.ProjectRepository;
import com.ideas2it.employeemanagement.service.ProjectService;
import com.ideas2it.employeemanagement.util.Constants;
import com.ideas2it.employeemanagement.util.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.util.mapper.Mapper;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public ProjectDto insertProject(ProjectDto projectDto) {
		return Mapper.convertIntoDto(projectRepository.save(Mapper.convertIntoEntity(projectDto)));
	}

	public List<ProjectDto> getProjects() throws EmployeeManagementException {
		List<ProjectDto> projects = Mapper.convertIntoProjectsDto(projectRepository.findAll());
		if (projects.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND, "404", HttpStatus.NOT_FOUND);
		}
		return projects;
	}

	public ProjectDto getProjectById(int id) throws EmployeeManagementException {
		return Mapper.convertIntoDto(projectRepository.findById(id).orElseThrow(
				() -> new EmployeeManagementException(Constants.PROJECT_NOT_FOUND, "404", HttpStatus.NOT_FOUND)));
	}

	public String deleteProjectById(int id) throws EmployeeManagementException {
		if (projectRepository.existsById(id)) {
			projectRepository.deleteById(id);
		} else {
			throw new EmployeeManagementException(Constants.PROJECT_NOT_FOUND, "404", HttpStatus.NOT_FOUND);
		}
		return "deleted successfully " + id;
	}

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
				if (existingProject.equals(projectRepository.save(Mapper.convertIntoEntity(projectDto)))) {
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
