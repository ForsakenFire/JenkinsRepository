package spring9_orm.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
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
	
	/**
	 * hibernate的异常转换处理：编译异常转为RuntimeException,无需手动try catch
	 * @return
	 */
	@Bean
	public BeanPostProcessor persistenceTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	/**
	 * 基于JPA的操作：需要配置实体管理工厂（EntityManagerFactory），分为应用程序管理和容器管理两大类型<br>
	 *（1）应用程序管理类型：无需javaee容器，由应用本身管理。需要一个persistence.xml配置文件，位于类路径下的 META-INF（不推荐）<br>
	 *（2）容器管理：可以将数据源信息配置在spring的上下文中，spring容器进行管理<br>
	 *JpaVendorAdapter：jpa适配器，用于指明使用的是哪个jpa实现（理解为JPA是一个接口，hibernate是实现）
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,JpaVendorAdapter jpaVendorAdapter){
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource);
		emfb.setJpaVendorAdapter(jpaVendorAdapter);
		//设置扫描的bean，无需配置映射xml
		emfb.setPackagesToScan("spring9_orm.bean");
		return emfb;
	}
	
	/**
	 * JPAVendorAdaptor的bean，这里使用hibernate的jpa实现
	 */
	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		//如果实体对应的表不存在会自动创建
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		return adapter;
	}
}
