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
	 * <p>
	 * To Create the employee.
	 * </p>
	 *
	 * @param projectName - the employee name to be added
	 * @param technology  - the employee address to be added
	 * @param startDate   - the employee date of birth to be added
	 * @param endDate     - the employee experience to be added
	 * @return the created project
	 */
	@Override
	public Project createProject(String name, Date startDate, Date endDate) throws EmployeeManagementException {
		Project project = new Project(name, startDate, endDate);
		return projectDao.insertProject(project);
	}

	/**
	 * <p>
	 * Generate the employee id as per the size of an employee map and return the
	 * employee id
	 * </p>
	 *
	 * @return an employee id with prefix as EMP
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
	 * <p>
	 * To fetch all the projects in the project table.
	 * </p>
	 *
	 * @return - the list of projects
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
	 * <p>
	 * To fetch the project for the given project id
	 * </p>
	 *
	 * @param projectId - a project id for which the project to be returned
	 * @return - the list of projects
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
	 * <p>
	 * To fetch the projects for the given employee id.
	 * </p>
	 *
	 * @param employeeId - an employee id for which the projects to be returned
	 * @return - the list of projects
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
	 * <p>
	 * To remove the project for the given project id.
	 * </p>
	 *
	 * @param projectId - a project id to be removed
	 * @return - true if the project is removed - false otherwise
	 * @throws EmployeeManagementException - if any sql exception is thrown
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