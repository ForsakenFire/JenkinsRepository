package spring15_socket.config;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 配置根上下文（Spring的IOC容器上下文）,扫描时将springmvc的controller排除
 * @author TD
 *
 */
@Configuration
@ComponentScan(basePackages="spring15_scoket",
	excludeFilters={@Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)})
public class RootConfig {
	
}
