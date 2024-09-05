package com.ProFit.hibernateutil;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class SessionFactoryListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.closeSessionFactory();
		System.out.println("SessionFactory Closed");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory();
		System.out.println("SessionFactory Created");
	}

}
