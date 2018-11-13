package spring11_cache.config;



import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@ImportResource(locations="classpath:applicationContext.xml")
@EnableJpaRepositories(basePackages="spring11_cache.dao")
public class JpaConfig {
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
		emfb.setPackagesToScan("spring11_cache.bean");
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
	
	/**
	 * JPA的事务管理器
	 * @param sessionFactory
	 * @return
	 */
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
		JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
		return transactionManager;
	}
}
