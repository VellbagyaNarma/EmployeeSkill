package com.niit.hrfrontend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.niit")
public class AppConfig {
	
	/*Registrating Spring related beans in Java configuration. 
	 * <context:component-scan/> @Componen Scan Scan starts from base package and registers all controllers, repositories, service, beans, etc.
	 * <mvc:annotation-driven/> @EnableWebMVC 	Enable Spring MVC-specific annotations like @Controller
	 * Spring Configuration file @Configuration Treat as the configuration file for Spring MVC-enabled application.
	 */
	
	@Bean(name="viewResolver")
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		
		return viewResolver;
		
	}

}
