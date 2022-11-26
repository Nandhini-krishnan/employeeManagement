package com.ideas2it.employeemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ideas2it.employeemanagement.model.ProjectDto;
import com.ideas2it.employeemanagement.model.TechStackDto;
import com.ideas2it.employeemanagement.repository.ProjectRepository;
import com.ideas2it.employeemanagement.service.ProjectService;
import com.ideas2it.employeemanagement.util.mapper.Mapper;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	public ProjectDto insertProject(ProjectDto projectDto) {
		return Mapper.convertIntoDto(projectRepository.save(Mapper.convertIntoEntity(projectDto)));
	}

	public List<ProjectDto> getProjects() {
		return Mapper.convertIntoProjectsDto(projectRepository.findAll());
	}

	public ProjectDto getProjectById(int id) {
		return Mapper.convertIntoDto(projectRepository.findById(id).orElse(null));
	}

	public String deleteProjectById(int id) {
		projectRepository.deleteById(id);
		return "deleted successfully " + id;
	}

	public String updateProject(ProjectDto projectDto, int id) {
		String message = null;
		if (projectRepository.existsById(id)) {
			ProjectDto existingProject = Mapper.convertIntoDto(projectRepository.findById(id).orElse(null));
			existingProject.setName(projectDto.getName());
			existingProject.setStartDate(projectDto.getStartDate());
			existingProject.setEndDate(projectDto.getEndDate());
			if (null != projectDto.getTechStacks()) {
				List<TechStackDto> input = existingProject.getTechStacks();
				input.addAll(projectDto.getTechStacks());
				existingProject.setTechStacks(input);
			}
			projectRepository.save(Mapper.convertIntoEntity(existingProject));	
		    message = projectDto.getName() + " Update Successfully";
		} else {
			message = "Project not found";
		}
		return message;
	}

}
