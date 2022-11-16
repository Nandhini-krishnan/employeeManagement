package com.ideas2it.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.ideas2it.dao.ProjectDao;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Project;
import com.ideas2it.util.HibernateUtil;
import com.ideas2it.util.exception.EmployeeManagementException;
import com.ideas2it.util.logger.EmployeeManagementLogger;

/**
 * <p>
 * ProjectDaoImpl has the methods implementation of ProjectDao to handle
 * project's operations.
 * </p>
 *
 * @author Naganandhini version 1.0 19-SEP-2022
 */
public class ProjectDaoImpl implements ProjectDao {
	private SessionFactory factory = HibernateUtil.getSessionFactory();

	/**
	 * {@inheritdoc}
	 */
	@Override
	public Project insertProject(Project project) throws EmployeeManagementException {
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(project);
			session.getTransaction().commit();
			if (0 == project.getId()) {
				throw new EmployeeManagementException(project.getName() + " insertion unsuccessful");
			}
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to insert " + project.getName(),
					hibernateException);
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return project;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Project> retrieveProjects() throws EmployeeManagementException {
		Session session = null;
		try {
			session = factory.openSession();
			Query<Project> query = session
					.createQuery("select p from Project p left join fetch p.employees e where p.isDeleted = false");
			List<Project> projects = query.getResultList();
			Query<Project> query2 = session.createQuery(
					"select p from Project p left join fetch p.techStacks e where p in :projects and p.isDeleted = false");
			return query2.setParameter("projects", projects).getResultList();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to fetch the projects ", hibernateException);
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Project> retrieveProjectsByEmployeeId(int employeeId) throws EmployeeManagementException {
		Session session = null;
		try {
			session = factory.openSession();
			Query<Project> query = session
					.createQuery("select p from Project p left join fetch p.techStacks t where p.isDeleted = false");
			List<Project> projects = query.getResultList();
			Query<Project> query2 = session.createQuery(
					"select p from Project p left join fetch p.employees e where p in :projects and e.id = :employeeId and p.isDeleted = false");
			return query2.setParameter("projects", projects).setParameter("employeeId", employeeId).getResultList();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to fetch the projects ", hibernateException);
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public Project retrieveProjectById(int projectId) throws EmployeeManagementException {
		Session session = null;
		Project project = null;
		try {
			session = factory.openSession();
			Query<Project> query = session.createQuery("select p from Project p left join fetch p.employees e where p.isDeleted = false");
			List<Project> projects = query.getResultList();
			Query<Project> query2 = session.createQuery("select p from Project p left join fetch p.techStacks e where p in :projects and p.id = :projectId and p.isDeleted = false");
			return query2.setParameter("projects", projects).setParameter("projectId", projectId).getSingleResult();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to fetch the project: " + projectId,
					hibernateException);
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public boolean deleteProjectById(int projectId) throws EmployeeManagementException {
		int count = 0;
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaUpdate<Project> criteriaUpdate = builder.createCriteriaUpdate(Project.class);
			Root<Project> root = criteriaUpdate.from(Project.class);
			criteriaUpdate.set("isDeleted", true).where(builder.equal(root.get("id"), projectId));
			count = session.createQuery(criteriaUpdate).executeUpdate();
			session.getTransaction().commit();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to delete the project: " + projectId,
					hibernateException);
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return 0 != count;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public boolean updateProject(Project project) throws EmployeeManagementException {
		Session session = null;
		boolean isUpdated = false;
		try {
			session = factory.openSession();
			session.beginTransaction();
			session.update(project);
			session.getTransaction().commit();
			isUpdated = true;
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while updating the project:" + project.getId(),
					hibernateException);
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return isUpdated;
	}
}