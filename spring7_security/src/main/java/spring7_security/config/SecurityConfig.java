package spring7_security.config;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * 如果不使用springmvc，配置为EnableWebSecurity
 * 使用EnableWebMvcSecurity时增加的部分：
 * （1）使用spring表单提交时会添加一个伪造的token输入隐藏域
 * （2）通过带有AuthenticationPrincipal注解的参数获取用户的username
 * 通过重写三个configure方法完成具体配置
 * @author TD
 *
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	/**jdbc验证时所需要的数据源*/
	@Autowired
	DataSource dataSource;
	
	/**
	 * 为认证过程提供用户存储功能，否则相当于没有用户，即使认证通过后也无法登陆
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*一：基于内存的用户存储:roles方法会为参数加上前缀：ROLE_XX。相当于authrities("ROLE_USER")
		*/
		auth.inMemoryAuthentication().withUser("user1")
		.password("123").roles("USER").and().withUser("user1")
		.password("456").roles("USER","ADMIN");
		
		/*二：基于数据库的用户存储：参数为数据源
		 * 1：需要配置三个查询sql：
		 * （1）usersByUsernameQuery:通过username查询密码和是否启用（字段为：用户名，密码，是否启用）
		 * （2）authoritiesByUsernameQuery:通过username查询权限（用户名，字符串型权限）
		 * （3）groupAuthoritiesByUsername:查询用户作为群组成员所赋予的权限（用户名，群组id，群组名，权限）
		 *:2：最后加入了密码转码器:PasswordEncoder接口的实现类作为参数，可自定义。
		 * 实现方法：
		 * （1）encode：编码
		 * （2）matches：返回值为boolean，判断库中转码后与输入的内容转码后是否匹配
		 */
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username,password,true"+"from test_user where username = ?")
		.authoritiesByUsernameQuery("select  username,'ROLE_USER' from test_user where username = ?")
		.passwordEncoder(new StandardPasswordEncoder("utf-8"));
		
		/*三：LDAP验证
		 * LDAP：轻量目录访问协议。目录结构。很像数据库，但是功能集中在查询，其他操作功能基本没有
		 * 相当于密码本。
		 * 其后所添加的方法都作为查询条件使用:
		 * xxBase:指定基础查询条件，缩小范围
		 * passwordCompare:默认通过ldap服务器认证用户，调用后通过进行密码比对进行认证（在LDAP服务器进行，保证私密性）
		 * passwordAttribute：默认情况下与ldap条目中的userPassword字段进行比对，此方法设置密码字段
		 * 可以添加encoder对密码进行加密，要求ldap服务器上用同样的规则进行加密
		 * 默认监听本机33389端口作为服务器，远程服务器时使用contextSource。
		 * 
		 */
		auth.ldapAuthentication().userSearchBase("ou=people").userSearchFilter("(uid={0})").groupSearchFilter("member={0}")
		.passwordCompare()
		.passwordEncoder(new Md5PasswordEncoder())
		.passwordAttribute("password")
		.and().contextSource().url("ldap://habuma.com:389/dc=habuma,dc=com");
	}
	
	/**
	 * 配置保护http请求的方案：哪些请求需要认证，以及需要的权限
	 * 默认为所有请求都需要认证
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		super.configure(http);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}
}
