package spring8_jdbc.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 配置根上下文（Spring的IOC容器上下文）,扫描时将springmvc的controller排除
 * @author TD
 *
 */
@Configuration
@ComponentScan(basePackages="spring8_jdbc",
	excludeFilters={@Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)})
@ImportResource(locations="classpath:applicationContext.xml")
public class RootConfig {
	/**
	 * 创建jdbctemplate的bean
	 * 也可以创建NamedParameterJdbcTemplate：sql中的参数使用 <b>：name</b>进行绑定
	 * 此时的operation需要换成NamedParameterJdbcOperations
	 * 参数可放置于一个map中
	 * @param dataSource
	 * @return
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
	
}
