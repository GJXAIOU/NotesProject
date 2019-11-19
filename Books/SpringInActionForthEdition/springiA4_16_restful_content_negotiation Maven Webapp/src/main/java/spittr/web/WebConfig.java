package spittr.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableWebMvc
@ComponentScan("spittr.web")
public class WebConfig extends WebMvcConfigurerAdapter {

	//默认的视图解析器
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Configuration
	public static class ContentNegotiationConfig extends WebMvcConfigurerAdapter {

		/*
		 * 有三种配置ContentNegotiationManager的方法：
		 * 直接声明一个ContentNegotiationManager类型的bean；（不常用，比较复杂）
		 * 通过ContentNegotiationManagerFactoryBean间接创建bean；（xml配置常用）
		 * 重载WebMvcConfigurerAdapter的configureContentNegotiation()方法。（Java配置常用）
		 */
		
		@Bean
		public ViewResolver cnViewResolver(ContentNegotiationManager cnm) {
			/*
			 * 内容协商视图解析器
			 * 1．确定请求的媒体类型；
			 * 		首先查看URL的文件扩展名，如果扩展名是“.json”，那么所需的内容类型必须是application/json
			 * 		没找到扩展名，则从请求中的Accept头部信息中找
			 * 		都没有，ContentNegotiatingViewResolver将会使用“/”作为默认的内容类型，
			 * 			这就意味着客户端必须要接收服务器发送的任何形式的表述
			 * 2．找到适合请求媒体类型的最佳视图
			 */
			ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
			cnvr.setContentNegotiationManager(cnm);
			return cnvr;
		}

		@Override
		public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
			configurer.defaultContentType(MediaType.TEXT_HTML);//默认为HTML
		}

		//以bean的形式查找视图
		@Bean
		public ViewResolver beanNameViewResolver() {
			return new BeanNameViewResolver();
		}

		//将“spittles”定义为json视图
		@Bean
		public View spittles() {
			return new MappingJackson2JsonView();
		}

		/*
		 * 在本例中，如果逻辑视图的名称为“spittles”，那么我们所配置的BeanNameViewResolver将会
		 * 解析spittles()方法中所声明的View。这是因为bean名称匹配逻辑视图的名称。
		 * 如果没有匹配的View的话，ContentNegotiatingViewResolver将会采用默认的行为，将其输出为HTML
		 * 
		 * 测试访问http://localhost:8110/springiA4_16_restful_content_negotiation/spittles.json
		 */
	}

	/*
	 * Spring提供了两种方法将资源的Java表述形式转换为发送给客户端的表述形式：
	 *  内容协商（Content negotiation）：选择一个视图，它能够将模型渲染为呈现给客户端的表述形式； 
	 *  消息转换器（Message conversion）：通过一个消息转换器将控制器所返回的对象转换为呈现给客户端的表述形式。
	 *  
	 *  内容协商视图解析器（ContentNegotiatingViewResolver）：
	 *  	优点：它在Spring MVC之上构建了REST资源表述层，控制器代码无需修改。
	 *  		相同的一套控制器方法能够为面向人类的用户产生HTML内容，也能针对不是人类的客户端产生JSON或XML。
	 *  	缺点：1）如果面向人类用户的接口与面向非人类客户端的接口之间有很多重叠的话，那么内容协商是一种很便利的
	 *  		方案。在实践中，面向人类用户的视图与REST API在细节上很少能够处于相同的级别。如果面向人类用户的
	 *  		接口与面向非人类客户端的接口之间没有太多重叠的话，那么ContentNegotiatingViewResolver的
	 *  		优势就体现不出来了。
	 *  		2）作为ViewResolver的实现，它只能决定资源该如何渲染到客户端，并没有涉及到客户端要发送什么样的
	 *  			表述给控制器使用。如果客户端发送JSON或XML的话，那么ContentNegotiatingViewResolver
	 *  			就无法提供帮助了
	 */

}
