package com.ideas2it.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Project;
import com.ideas2it.model.TechStack;

/**
 * <p>
 * This is a utility class for getting the hibernate session object.
 * </p>
 *
 * @author  Naganandhini
 *
 * @version  1.0  10 Oct 2022
 *
 */
public class HibernateUtil {
    private static SessionFactory factory;

    private HibernateUtil() {
	Configuration configuration = new Configuration()
		.configure("hibernate.cfg.xml")
		.addAnnotatedClass(Employee.class)
		.addAnnotatedClass(Project.class)
		.addAnnotatedClass(TechStack.class);
	factory = configuration.buildSessionFactory();
    }

    /**
     * <p>
     * This method creates an object for SessionFactory
     * </p>
     *
     * @return - the session factory object
     */
    public static SessionFactory getSessionFactory()  {
	if (null == factory || factory.isClosed()) {
	    new HibernateUtil();
	}
	return factory;
    }

}