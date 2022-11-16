package com.ideas2it.service.impl;

import java.util.List;

import com.ideas2it.dao.TechStackDao;
import com.ideas2it.dao.impl.TechStackDaoImpl;
import com.ideas2it.model.Project;
import com.ideas2it.model.TechStack;
import com.ideas2it.service.TechStackService;
import com.ideas2it.util.Constants;
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
public class TechStackServiceImpl implements TechStackService {
	private TechStackDao techStackDao = new TechStackDaoImpl();

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
	public TechStack createTechStack(String name, float version) throws EmployeeManagementException {
		TechStack techStack = new TechStack(name, version);
		return techStackDao.insertTechStack(techStack);
	}

	/**
	 * <p>
	 * Generate the employee id as per the size of an employee map and return the
	 * employee id
	 * </p>
	 *
	 * @return an employee id with prefix as EMP
	 */
	/*
	 * public String generateProjectId() throws EmployeeManagementException { int
	 * projectId = projectDao.getProjectCount(); return "PROJ" + (++projectId); }
	 */

	/**
	 * <p>
	 * To fetch all the projects in the project table.
	 * </p>
	 *
	 * @return - the list of projects
	 */
	@Override
	public List<TechStack> getTechStacks() throws EmployeeManagementException {
		return techStackDao.retrieveTechStacks();
	}

	/**
	 * <p>
	 * To fetch the project for the given project id
	 * </p>
	 *
	 * @param projectId - a project id for which the project to be returned
	 * @return - the project
	 */
	@Override
	public TechStack getTechStackById(int techStackId) throws EmployeeManagementException {
		TechStack techStack = techStackDao.retrieveTechStackById(techStackId);
		if (null == techStack) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND);
		}
		return techStack;
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
	public List<TechStack> getTechStacksByProjectId(int projectId) throws EmployeeManagementException {
		List<TechStack> techStacks = techStackDao.retrieveTechStacksByProjectId(projectId);
		if (techStacks.isEmpty()) {
			throw new EmployeeManagementException(Constants.NO_RECORD_FOUND);
		}
		return techStacks;
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
	public boolean removeTechStackById(int techStackId) throws EmployeeManagementException {
		return techStackDao.deleteTechStackById(techStackId);
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public boolean isIdExist(int techStackId) throws EmployeeManagementException {
		if (null != getTechStackById(techStackId)) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public boolean updateTechStack(TechStack techStack) throws EmployeeManagementException {
		boolean isUpdated = techStackDao.updateTechStack(techStack);
		if (!isUpdated) {
			throw new EmployeeManagementException("TechStack not updated");
		}
		return isUpdated;
	}
}