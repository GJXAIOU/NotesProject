
 /**
 * FileName:     ThymeleafConfig.java
 * Copyright (c) 2019 lzc.All Rights Reserved.
 */

package spittr.config;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Description:   
 * @author:     lzc  
 * @version:    1.0  
 * @date:       2019-04-18 17:42:27  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-04-18   lzc         1.0         1.0 Version  
 */

@Configuration
public class ThymeleafConfig {

	@Bean
	public SpringTemplateEngine templateEngine(Set<TemplateResolver> templateResolvers) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolvers(templateResolvers);
		return templateEngine;
	}
	
	@Bean
	public ClassLoaderTemplateResolver emailTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/");
		templateResolver.setTemplateMode("HTML5");
		//减轻thymeleaf对HTML语法的疯狂检查
//	    templateResolver.setTemplateMode("LEGACYHTML5");
		templateResolver.setCharacterEncoding("UTF-8");
	    templateResolver.setOrder(1);
		return templateResolver;
	}
}
