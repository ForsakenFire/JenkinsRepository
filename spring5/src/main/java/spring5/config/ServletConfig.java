package spring5.config;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler.referenceInsertExecutor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * 配置DispatcherServlet
 * @author TD
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("spring5.web")
public class ServletConfig extends WebMvcConfigurerAdapter{

	//使用jsp和apache tiles使用的视图解析器，需要时加上bean注解即可
	@Bean
	public ViewResolver viewResolver(){
		/**适用于jsp的视图解析
		 * 
		 */
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		 
		/**适用于tile视图解析:使用tils3
		TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		*/
		return resolver;
	}
	
	/**
	 * 生成thymeleaf使用的视图解析器并为解析器注入模板引擎
	 * @param templateEngine
	 * @return
	 */
	public ViewResolver viewResolver(SpringTemplateEngine templateEngine){
		//适用于thymeleaf的视图解析器
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine);
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}
	
	/**
	 * 生成thymeleaf模板引擎,并为模板引擎注入模板解析器
	 * @return
	 */
	public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver){
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver);
		return engine;
	}
	
	/**
	 * thymeleaf模板解析器
	 * @return
	 */
	public ITemplateResolver templateResolver(){
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		// ServletContextTemplateResolver需要一个ServletContext作为构造参数
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(webApplicationContext.getServletContext());
		templateResolver.setPrefix("/WEB-INF/template/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("utf-8");
		return templateResolver;
	}
	
	
	
	
	/**
	 * apache tiles使用
	 * @return
	 */
	public TilesConfigurer tilesConfigurer(){
		TilesConfigurer tiles = new TilesConfigurer();
		//设置tiles定义xml，可以使用通配符
		tiles.setDefinitions(new String[]{"/WEB-INF/layout/tiles.xml"});
		tiles.setCheckRefresh(true);
		return tiles;
	}
	
	
	/**
	 * 国际化的资源文件配置，建议通过拦截器进行支持
	 * @return
	 */
	@Bean
	public MessageSource messageSource(){
		/**可能需要重启
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		//属性文件的基础名，接后缀等，项目路径
		messageSource.setBasename("message-international");
		*/
		//无需重启和编译，basename可以写系统路径或项目路径
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:message");
		messageSource.setCacheSeconds(10);
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
	
	@Override
	/**
	 * 静态资源使用默认的servlet容器处理
	 */
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
