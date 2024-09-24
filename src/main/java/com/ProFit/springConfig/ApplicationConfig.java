package com.ProFit.springConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.util.Properties;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = {"com.ProFit.bean","com.ProFit.dao","com.ProFit.service"})
@EnableTransactionManagement
public class ApplicationConfig {
    // 定義 DataSource Bean，通過 JNDI 獲取資料庫連接
    @Bean
    public DataSource dataSource() throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
        jndiBean.setJndiName("java:comp/env/connectSQLServerConn/OrderSystem");
        // jndiBean.setJndiName("java:comp/env/connectMySQLConn/OrderSystem");
        jndiBean.afterPropertiesSet();
        DataSource ds = (DataSource) jndiBean.getObject();
        return ds;
    }
    
    // 定義 Hibernate SessionFactory Bean
    @Bean
    public LocalSessionFactoryBean sessionFactory() throws IllegalArgumentException, NamingException {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.ProFit");
        factoryBean.setHibernateProperties(additionalProperties());
        return factoryBean;
    }

    // 設定 Hibernate 的附加屬性
    private Properties additionalProperties() {
        Properties props = new Properties();
        props.put("hibernate.dialect", org.hibernate.dialect.SQLServerDialect.class);
        // props.put("hibernate.dialect", org.hibernate.dialect.MySQLDialect.class);
        props.put("hibernate.show_sql", Boolean.TRUE);
        props.put("hibernate.format_sql", Boolean.TRUE);
        // props.put("hibernate.current_session_context_class", "thread");
        return props;
    }
    
    // 定義 HibernateTransactionManager Bean，用於管理事務
    @Bean
    public HibernateTransactionManager transactionManager() throws IllegalArgumentException, NamingException {
        HibernateTransactionManager txMgr = new HibernateTransactionManager();
        txMgr.setSessionFactory(sessionFactory().getObject());
        return txMgr;
    }
}