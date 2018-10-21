package spring7_security.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 实现了WebApplicationInitializer，spring会发现并在web容器中注册DelegatingFilterProxy。<br>
 * DelegatingFilterProxy会将请求拦截并委托给FilterChainProxy处理
 * @author TD
 */
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer{

}
