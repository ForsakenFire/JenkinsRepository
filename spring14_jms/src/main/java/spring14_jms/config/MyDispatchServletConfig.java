package spring14_jms.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 用于将springmvc的前端servlet配置到ServletContext，省略了在web.xml中配置的过程<br>
 * 需要继承AbstractAnnotationConfigDispatcherServletInitializer。<br>
 * 继承该类的所有类都会自动配置DispatcherServlet和Spring的应用上下文（位于应用的ServletContext中）
 * 
 * @since Servlet3.0
 * @author TD
 *
 */
public class MyDispatchServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	/**
	 * 指定spring容器的配置类，初始化spring的ioc容器。类似于applicationContext.xml<br>
	 * 扫描时无需扫描controller
	 * 
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[]{RootConfig.class};
	}

	/**
	 * 指定springmvc的配置类，初始化springmvc的上下文,类似于springmvc-config.xml<br>
	 * 父容器为spring的ioc容器，所以可以获取spring上下文中的bean，但是反过来不行<Br>
	 * Controller的扫描需要在springmvc的配置文件中进行扫描
	 * 
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[]{ServletConfig.class};
	}

	/**
	 * 设置DispatcherServlet的映射url
	 */
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{"*.do"};
	}

}
