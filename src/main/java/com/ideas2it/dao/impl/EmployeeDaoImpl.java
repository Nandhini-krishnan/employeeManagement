package com.ideas2it.dao.impl;

import java.util.Date;
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

import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.model.Employee;
import com.ideas2it.util.HibernateUtil;
import com.ideas2it.util.exception.EmployeeManagementException;
import com.ideas2it.util.logger.EmployeeManagementLogger;

/**
 * <p>
 * EmployeeDaoImpl has the methods implementation of EmployeeDao to handle
 * employee's operations.
 * </p>
 *
 * @author Naganandhini version 1.0 19-SEP-2022
 */
public class EmployeeDaoImpl implements EmployeeDao {
	private SessionFactory factory = HibernateUtil.getSessionFactory();

	/**
	 * {@inheritdoc}
	 */
	@Override
	public Employee insertEmployee(Employee employee) throws EmployeeManagementException {
		Session session = null;
		try {
			System.out.println(employee);
			session = factory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(employee);
			session.getTransaction().commit();
			if (0 == employee.getId()) {
				throw new EmployeeManagementException(employee.getName() + " insertion unsuccessful");
			}
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			System.out.println(hibernateException.getMessage());
			throw new EmployeeManagementException("Error: while trying to insert " + employee.getName(),
					hibernateException);
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return employee;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Employee> retrieveEmployees() throws EmployeeManagementException {
		Session session = null;
		try {
			session = factory.openSession();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			root.fetch("projects", JoinType.LEFT);
			criteriaQuery.select(root).distinct(true).where(criteriaBuilder.equal(root.get("isDeleted"), false));
			return session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to fetch the employees ", hibernateException);
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
	public List<Employee> searchEmployees(String input) throws EmployeeManagementException {
		Session session = null;
		try {
			session = factory.openSession();
			String value = "%" + input + "%";
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			root.fetch("projects", JoinType.LEFT);
			criteriaQuery
					.select(root).distinct(
							true)
					.where(criteriaBuilder.and(
							criteriaBuilder.or(criteriaBuilder.like(root.get("name"), value),
									criteriaBuilder.like(root.get("address"), value),
									criteriaBuilder.like(root.get("bloodGroup").as(String.class), value),
									criteriaBuilder.like(root.get("previousOrganisationName"), value)),
							criteriaBuilder.equal(root.get("isDeleted"), false)));
			return session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to fetch the employees ", hibernateException);
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
	public Employee retrieveEmployeeById(int employeeId) throws EmployeeManagementException {
		Session session = null;
		Employee employee = null;
		try {
			session = factory.openSession();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			root.fetch("projects", JoinType.LEFT);
			criteriaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get("isDeleted"), false),
					criteriaBuilder.equal(root.get("id"), employeeId)));
			employee = session.createQuery(criteriaQuery).getSingleResult();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to fetch the employee: " + employeeId,
					hibernateException);
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return employee;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Employee> retrieveEmployeesByProjectId(int projectId) throws EmployeeManagementException {
		Session session = null;
		try {
			session = factory.openSession();
			Query<Employee> query = session.createQuery(
					"select distinct e from Employee e right join fetch e.projects p where p.id = :projectId and p.isDeleted = false");
			return query.setParameter("projectId", projectId).getResultList();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to fetch the employees ", hibernateException);
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
	public List<Employee> retrieveEmployeesByExperience(int yearsOfExperience) throws EmployeeManagementException {
		Session session = null;
		try {
			session = factory.openSession();
			Query<Employee> query = session.createQuery(
					"select distinct e from Employee e join fetch e.projects p where e.isDeleted = false and e.dateOfJoin < concat(year(current_date()) - :yearsOfExperience, '-',month(current_date()),'-',day(current_date()))");
			return query.setParameter("yearsOfExperience", yearsOfExperience).getResultList();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to fetch the employees ", hibernateException);
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Employee> retrieveEmployeesInRange(Date startDate, Date endDate) throws EmployeeManagementException {
		Session session = null;
		try {
			session = factory.openSession();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			root.fetch("projects", JoinType.LEFT);
			criteriaQuery.select(root).distinct(true)
					.where(criteriaBuilder.and(criteriaBuilder.between(root.get("dateOfJoin"), startDate, endDate),
							criteriaBuilder.equal(root.get("isDeleted"), false)));
			return session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to fetch the employees ", hibernateException);
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
	public List<Employee> retrieveEmployeesByMultipleId(List<Integer> listOfId) throws EmployeeManagementException {
		Session session = null;
		try {
			session = factory.openSession();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			root.fetch("projects", JoinType.LEFT);
			criteriaQuery.select(root).distinct(true).where(criteriaBuilder.and(root.get("id").in(listOfId),
					criteriaBuilder.equal(root.get("isDeleted"), false)));
			return session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to fetch the employees ", hibernateException);
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
	public boolean deleteEmployeeById(int employeeId) throws EmployeeManagementException {
		boolean isRemoved = false;
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaUpdate<Employee> criteriaUpdate = builder.createCriteriaUpdate(Employee.class);
			Root<Employee> root = criteriaUpdate.from(Employee.class);
			criteriaUpdate.set("isDeleted", true).where(builder.equal(root.get("id"), employeeId));
			int count = session.createQuery(criteriaUpdate).executeUpdate();
			session.getTransaction().commit();
			if (0 != count) {
				isRemoved = true;
			}
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while trying to delete the employee: " + employeeId,
					hibernateException);
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return isRemoved;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public boolean updateEmployee(Employee employee) throws EmployeeManagementException {
		Session session = null;
		boolean isUpdated = false;
		try {
			System.out.println(employee);
			session = factory.openSession();
			session.beginTransaction();
			session.update(employee);
			session.getTransaction().commit();
			isUpdated = true;
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while updating the employee:" + employee.getId(),
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
	public Long getEmployeeCount() throws EmployeeManagementException {
		Long count;
		Session session = null;
		try {
			session = factory.openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Long> query = builder.createQuery(Long.class);
			query.multiselect(builder.count(query.from(Employee.class)));
			count = session.createQuery(query).getSingleResult();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			throw new EmployeeManagementException("Error: while getting the employee count " + hibernateException);
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return count;
	}
}