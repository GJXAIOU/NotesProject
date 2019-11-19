package spittr.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login")//默认登录页。如果没有配置loginPage，将启动默认的登录页
			.and().logout()//LogoutFilter拦截/logout请求，remember_me token的信息会清空
				.logoutSuccessUrl("/")//logout后重定向到"/"
//				.logoutUrl("/signout")//重写LogoutFilter默认的拦截路径（/logout）
			.and().rememberMe()//开启记住当前用户的功能
				.tokenRepository(new InMemoryTokenRepositoryImpl())//token存储在内存
				.tokenValiditySeconds(2419200).key("spittrKey")//客户端在cookie中存储token，时间2419200s
			.and().httpBasic().realmName("Spittr")//启用http basic认证，realmName指定具体域
			.and().authorizeRequests()
				.antMatchers("/").authenticated()
				.antMatchers("/spitter/me").authenticated()//须认证该路径的请求
				.antMatchers(HttpMethod.POST, "/spittles").authenticated()//须认证该路径的post请求
				.anyRequest().permitAll();//其它请求无须认证
		
//		http.authorizeRequests()
//			.antMatchers("/spitter/me").hasRole("SPITTER")//限定角色
//			.antMatchers(HttpMethod.POST, "/spittles").hasRole("SPITTER")//限定角色
//			.anyRequest().permitAll();//其它请求无须认证
		
		//使用SpEL
//		http.authorizeRequests()
//			.antMatchers("/spitter/me")
//			.access("hasRole('ROLE_SPITTER') AND hasIpAddress('192.168.1.2')");
		
		//强制通道的安全性
//		http.requiresChannel()
//			.antMatchers("/spitter/form").requiresSecure()//需要HTTPS
//			.antMatchers("/").requiresInsecure();//声明始终通过HTTP传送
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 启用内存用户存储
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
			.and().withUser("admin").password("password").roles("USER", "ADMIN");
		//基于数据库表进行验证
//		auth.jdbcAuthentication().dataSource(dataSource)
//			.usersByUsernameQuery("select username,password,true from Spitter where username=?")
//			.authoritiesByUsernameQuery("select username,ROLE_USER from Spitter where username=?")
//			.passwordEncoder(new StandardPasswordEncoder("53cr3t"));
		//基于LDAP进行认证
//		auth.ldapAuthentication()
////			.userSearchBase("ou=people")//指定从名为people的组织单元下搜索
//			.userSearchFilter("{uid={0}}")//搜索用户
////			.groupSearchBase("ou=groups")//指定从名为groups的组织单元下搜索
//			.groupSearchFilter("{member={0}")//搜索组
////			.passwordCompare()//默认情况下，在登录表单中提供的密码将会与用户的LDAP条目中的userPassword属性进行比对
////			.passwordEncoder(new Md5PasswordEncoder())
////			.passwordAttribute("password")//给定密码进行比对的是“passcode”属性
////			.contextSource().url("ldap://habuma.com:389/dc=habuma,dc=com")//远程ldap
////			.contextSource().root("dc=habuma,dc=com")//嵌入式ldap
////				.ldif("classpath:user.ldif")//加载指定的ldif文件
//			;
	}
}
