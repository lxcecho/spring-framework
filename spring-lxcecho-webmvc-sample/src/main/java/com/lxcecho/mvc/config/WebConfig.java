package com.lxcecho.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-04-11
 */
@Configuration
@EnableWebMvc // 启用 Spring MVC
@ComponentScan(basePackages = "com.lxcecho.mvc") // 扫描指定包中的组件
public class WebConfig {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
