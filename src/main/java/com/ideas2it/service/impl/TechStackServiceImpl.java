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
	 * {@inheritdoc}
	 */
	@Override
	public TechStack createTechStack(TechStack techStack) throws EmployeeManagementException {
		return techStackDao.insertTechStack(techStack);
	}

	/**
	 * {@inheritdoc}
	 */
	/*
	 * public String generateProjectId() throws EmployeeManagementException { int
	 * projectId = projectDao.getProjectCount(); return "PROJ" + (++projectId); }
	 */

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<TechStack> getTechStacks() throws EmployeeManagementException {
		return techStackDao.retrieveTechStacks();
	}

	/**
	 * {@inheritdoc}
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
	 * {@inheritdoc}
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
	 * {@inheritdoc}
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