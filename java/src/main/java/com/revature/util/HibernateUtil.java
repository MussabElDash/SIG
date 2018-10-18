package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/*
 * SessionFactory is configured as a singleton.
 * WE call the configure method of the configuration component which,
 * by default, will use the "hibernate.cfg.xml" file.
 * If named differently, you will have to supply the name as a 
 * parameter.
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory = createSessionFactory();

	private static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}
}
