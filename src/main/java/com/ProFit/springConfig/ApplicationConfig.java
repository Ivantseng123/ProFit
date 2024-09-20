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
@ComponentScan(basePackages = "com.ProFit")
@EnableTransactionManagement
public class ApplicationConfig {
	// 您可以在這裡添加@Bean方法來定義beans
	@Bean
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
		jndiBean.setJndiName("java:comp/env/connectSQLServerConn/OrderSystem");
		//jndiBean.setJndiName("java:comp/env/connectMySQLConn/OrderSystem");
		jndiBean.afterPropertiesSet();
		DataSource ds = (DataSource)jndiBean.getObject();
		return ds;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IllegalArgumentException, NamingException {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("com.ProFit");
		factoryBean.setHibernateProperties(additionalProperties());
		return factoryBean;
	}

	private Properties additionalProperties() {
		Properties props = new Properties();
		props.put("hibernate.dialect", org.hibernate.dialect.SQLServerDialect.class);
		//props.put("hibernate.dialect", org.hibernate.dialect.MySQLDialect.class);
		props.put("hibernate.show_sql", Boolean.TRUE);
		props.put("hibernate.format_sql", Boolean.TRUE);
		//props.put("hibernate.current_session_context_class", "thread");
		return props;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() throws IllegalArgumentException, NamingException {
		HibernateTransactionManager txMgr = new HibernateTransactionManager();
		txMgr.setSessionFactory(sessionFactory().getObject());
		return txMgr;
	}
}
