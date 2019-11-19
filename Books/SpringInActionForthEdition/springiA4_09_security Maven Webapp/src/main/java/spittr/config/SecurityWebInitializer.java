package spittr.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {
	/*
	 * AbstractSecurityWebApplicationInitializer实现了
	 * WebApplication-Initializer，因此Spring会发现它，并用它
	 * 在Web容器中注册DelegatingFilterProxy
	 */
}
