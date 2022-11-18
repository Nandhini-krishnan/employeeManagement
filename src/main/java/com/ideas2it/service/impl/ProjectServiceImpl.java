package com.ideas2it.service.impl;

import java.util.Date;
import java.util.List;

import com.ideas2it.dao.ProjectDao;
import com.ideas2it.dao.impl.ProjectDaoImpl;
import com.ideas2it.model.Project;
import com.ideas2it.model.TechStack;
import com.ideas2it.service.ProjectService;
import com.ideas2it.util.Constants;
import com.ideas2it.util.DateUtil;
import com.ideas2it.util.exception.EmployeeManagementException;

/**
 * <p>
 * ProjectServiceImpl has the methods implementations of ProjectService to
 * handle project's operations.
 * </p>
 *
 * @author Naganandhini
 * @version 1.0 10-Aug-2022
 */
public class ProjectServiceImpl implements ProjectService {
	private ProjectDao projectDao = new ProjectDaoImpl();

	/**
	 * {@inheritdoc}
	 */
	@Override
	public Project createProject(Project project) throws EmployeeManagementException {
		return projectDao.insertProject(project);
	}

	/**
	 * {@inheritdoc}
	 */
	// public String generateProjectId() throws EmployeeManagementException {
	// int projectId = projectDao.getProjectCount();
	// return "PROJ" + (++projectId);
	// }

	/**
	 * {@inheritdoc}
	 */
	@Override
	public Project assignTechStacks(int projectId, List<TechStack> techStacks) throws EmployeeManagementException {
		Project project = getProjectById(projectId);
		List<TechStack> input = project.getTechStacks();
		input.addAll(techStacks);
		project.setTechStacks(input);
		return projectDao.insertProject(project);
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Project> getProjects() throws EmployeeManagementException {
		List<Project> projects = projectDao.retrieveProjects();
		if (projects.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND);
		}
		return projects;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public Project getProjectById(int projectId) throws EmployeeManagementException {
		Project project = projectDao.retrieveProjectById(projectId);
		if (null == project) {
			throw new EmployeeManagementException(Constants.PROJECT_NOT_FOUND);
		}
		return project;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Project> getProjectsByEmployeeId(int employeeId) throws EmployeeManagementException {
		List<Project> projects = projectDao.retrieveProjectsByEmployeeId(employeeId);
		if (projects.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND);
		}
		return projects;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public boolean deleteProjectById(int projectId) throws EmployeeManagementException {
		return projectDao.deleteProjectById(projectId);
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public boolean isIdExist(int projectId) throws EmployeeManagementException {
		if (null != getProjectById(projectId)) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public boolean updateProject(Project project) throws EmployeeManagementException {
		boolean isUpdated = projectDao.updateProject(project);
	    if (!isUpdated) {
		    throw new EmployeeManagementException("project not updated");
	    }
	    return isUpdated;
	}
}