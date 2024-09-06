package com.ProFit.hibernateutil;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static final SessionFactory facotry = createSessionFactory();

	private static SessionFactory createSessionFactory() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		return sf;
	}

	public static SessionFactory getSessionFactory() {
		return facotry;
	}

	public static void closeSessionFactory() {
		if(facotry!=null) {
			facotry.close();
		}
	}

}
