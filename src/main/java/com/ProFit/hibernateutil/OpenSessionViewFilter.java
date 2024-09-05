package com.ProFit.hibernateutil;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = {"/*"})
public class OpenSessionViewFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			System.out.println("Transaction Begin");
			
			chain.doFilter(request, response);
			
			session.getTransaction().commit();
			System.out.println("Transaction Commit");
		}catch(Exception e) {
			session.getTransaction().rollback();
			System.out.println("Transaction Rollback");
			e.printStackTrace();
		}finally {
			System.out.println("Session Closed");
		}		
		
	}

}
