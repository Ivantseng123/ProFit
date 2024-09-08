package com.ProFit.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.HibernateException;

public class HibernateUtil {

    private static final SessionFactory factory = createSessionFactory();

    private static SessionFactory createSessionFactory() {
        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;
        try {
            // 創建 StandardServiceRegistry
            registry = new StandardServiceRegistryBuilder().configure().build();
            // 利用 MetadataSources 和 registry 來構建 SessionFactory
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (HibernateException e) {
            // 當初始化失敗時，記錄錯誤並銷毀 registry
            e.printStackTrace();
            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static void closeSessionFactory() {
        if (factory != null) {
            factory.close();
        }
    }
}
