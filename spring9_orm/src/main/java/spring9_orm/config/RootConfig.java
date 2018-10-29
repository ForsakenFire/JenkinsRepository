package spring9_orm.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 配置根上下文（Spring的IOC容器上下文）,扫描时将springmvc的controller排除
 * @author TD
 *
 */
@Configuration
@ComponentScan(basePackages="spring9_orm",
	excludeFilters={@Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)})
@ImportResource(locations="classpath:applicationContext.xml")
@EnableTransactionManagement
public class RootConfig {
	/**
	 * 生成sessionFactory的bean
	 * hibernate5中的Localsessionfactorybean包含了3中的所有功能，同时增加了对于注解的支持
	 * @param dataSource
	 * @return
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		//设置映射规则，支持注解扫描
		sessionFactoryBean.setPackagesToScan("spring9_orm.bean");
		Properties properties = new Properties();
		//设置sql的方言
		properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
		sessionFactoryBean.setHibernateProperties(properties);
		return sessionFactoryBean;
	}
	/**
	 * 事务管理器
	 * @param sessionFactory
	 * @return
	 */
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}
