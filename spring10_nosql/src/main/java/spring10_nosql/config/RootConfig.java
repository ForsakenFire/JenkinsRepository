package spring10_nosql.config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 配置根上下文（Spring的IOC容器上下文）,扫描时将springmvc的controller排除
 * @author TD
 *
 */
@Configuration
@ComponentScan(basePackages="spring10_nosql",
	excludeFilters={@Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)})
@ImportResource(locations="classpath:applicationContext.xml")
@PropertySources({
		@PropertySource("classpath:mongo.properties"),
		@PropertySource("classpath:redis.properties")
		})
public class RootConfig {
	
}
