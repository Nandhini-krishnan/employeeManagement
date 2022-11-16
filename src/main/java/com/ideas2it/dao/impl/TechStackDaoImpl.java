package com.ideas2it.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.ideas2it.dao.TechStackDao;
import com.ideas2it.model.Project;
import com.ideas2it.model.TechStack;
import com.ideas2it.util.HibernateUtil;
import com.ideas2it.util.exception.EmployeeManagementException;
import com.ideas2it.util.logger.EmployeeManagementLogger;

/**
 * <p>
 * TechStackDaoImpl has the methods implementation of ProjectDao to handle
 * project's operations.
 * </p>
 *
 * @author Naganandhini version 1.0 19-SEP-2022
 */
public class TechStackDaoImpl implements TechStackDao {
	private SessionFactory factory = HibernateUtil.getSessionFactory();

	/**
	 * {@inheritdoc}
	 */
	@Override
	public TechStack insertTechStack(TechStack techStack) throws EmployeeManagementException {
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(techStack);
			session.getTransaction().commit();
			if (0 == techStack.getId()) {
				throw new EmployeeManagementException(techStack.getName() + " insertion unsuccessful");
			}
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to insert " + techStack.getName(),
					hibernateException);
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return techStack;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<TechStack> retrieveTechStacks() throws EmployeeManagementException {
		Session session = null;
		try {
			session = factory.openSession();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<TechStack> criteriaQuery = criteriaBuilder.createQuery(TechStack.class);
			Root<TechStack> root = criteriaQuery.from(TechStack.class);
			root.fetch("projects", JoinType.LEFT);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("isDeleted"), false));
			return session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to fetch the tech stacks ", hibernateException);
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
	public List<TechStack> retrieveTechStacksByProjectId(int projectId) throws EmployeeManagementException {
		Session session = null;
		try {
			session = factory.openSession();
			Query<TechStack> query = session.createQuery(
					"select t from TechStack t join fetch t.projects p where p.id = :projectId and t.isDeleted = false");
			return query.setParameter("projectId", projectId).getResultList();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to fetch the tech stacks ", hibernateException);
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
	public TechStack retrieveTechStackById(int techStackId) throws EmployeeManagementException {
		Session session = null;
		TechStack techStack = null;
		try {
			session = factory.openSession();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<TechStack> criteriaQuery = criteriaBuilder.createQuery(TechStack.class);
			Root<TechStack> root = criteriaQuery.from(TechStack.class);
			root.fetch("projects", JoinType.LEFT);
			criteriaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get("isDeleted"), false),
					criteriaBuilder.equal(root.get("id"), techStackId)));
			techStack = session.createQuery(criteriaQuery).getSingleResult();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to fetch the tech stack: " + techStackId,
					hibernateException);
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return techStack;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public boolean updateTechStack(TechStack techStack) throws EmployeeManagementException {
		Session session = null;
		boolean isUpdated = false;
		try {
			session = factory.openSession();
			session.beginTransaction();
			session.update(techStack);
			session.getTransaction().commit();
			isUpdated = true;
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while updating the Tech Stack:" + techStack.getId(),
					hibernateException);
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return isUpdated;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public boolean deleteTechStackById(int techStackId) throws EmployeeManagementException {
		int count = 0;
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaUpdate<TechStack> criteriaUpdate = builder.createCriteriaUpdate(TechStack.class);
			Root<TechStack> root = criteriaUpdate.from(TechStack.class);
			criteriaUpdate.set("isDeleted", true).where(builder.equal(root.get("id"), techStackId));
			count = session.createQuery(criteriaUpdate).executeUpdate();
			session.getTransaction().commit();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to delete the tech stack: " + techStackId,
					hibernateException);
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return 0 != count;
	}
}