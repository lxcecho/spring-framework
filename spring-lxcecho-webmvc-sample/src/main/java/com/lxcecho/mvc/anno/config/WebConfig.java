package com.lxcecho.mvc.anno.config;

import com.lxcecho.mvc.anno.interceptor.FirstInterceptor;
import com.lxcecho.mvc.anno.interceptor.SecondInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * SpringMVC 对应组件的配置类 [声明 SpringMVC 需要的组件信息]
 * <p>
 * 导入 handlerMapping 和 handlerAdapter 的三种方式
 * 1.自动导入 handlerMapping和handlerAdapter [推荐]
 * 2.可以不添加，springmvc 会检查是否配置 handlerMapping和handlerAdapter，没有配置默认加载
 * 3.使用 @Bean 方式配置 handlerMapper 和 handlerAdapter
 *
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-04-11
 */
@Configuration
// TODO 注意：@EnableWebMvc： json 数据处理，必须使用此注解，因为他会加入 json 处理器
@EnableWebMvc // 启用 Spring MVC
@ComponentScan(basePackages = "com.lxcecho.mvc.anno") // 扫描指定包中的组件
public class WebConfig implements WebMvcConfigurer {
	// WebMvcConfigurer springMvc 进行组件配置的规范，配置组件，提供各种方法! 前期可以实现

	/**
	 * 配置 JSP 对应的视图解析器，针对 com.lxcecho.mvc.anno.controller.AnnoController#sayHi() 调试打开
	 *
	 * @return
	 */
	/*@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		System.out.println("viewResolver");
		return resolver;
	}*/

	/**
	 * 添加视图解析器（可以添加多个）
	 *
	 * @param registry
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(Ordered.LOWEST_PRECEDENCE);
		registry.viewResolver(resolver);
	}

	@Bean
	public HandlerMapping handlerMapping() {
		return new RequestMappingHandlerMapping();
	}

	@Bean
	public HandlerAdapter handlerAdapter() {
		return new RequestMappingHandlerAdapter();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
	}

	/**
	 * 配置静态资源访问处理器
	 *
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	/**
	 * 添加拦截器（可以添加多个）
	 *
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new FirstInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(new SecondInterceptor()).addPathPatterns("/**");
	}

}
