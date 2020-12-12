package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import spittr.service.ExpressionSecuredSpittleService;
import spittr.service.SpittleService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)//prePostEnabled为true，可以使用SpEL来保护方法应用
public class ExpressionSecurityConfig extends GlobalMethodSecurityConfiguration {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
    .inMemoryAuthentication()
      .withUser("user").password("password").roles("USER");
  }
  
  @Bean
  public SpittleService spitterService() {
    return new ExpressionSecuredSpittleService();
  }
  
  /*
   * Spring Security 3.0提供了4个新的注解，可以使用SpEL表达式来保护方法调用
   * @PreAuthorize 	在方法调用之前，基于表达式的计算结果来限制对方法的访问
   * @PostAuthorize	允许方法调用，但是如果表达式计算结果为false，将抛出一个安全性异常
   * @PostFilter 	允许方法调用，但必须按照表达式来过滤方法的结果
   * @PreFilter 	允许方法调用，但必须在进入方法之前过滤输入值
   */

}
