package com.lxcecho.mvc.anno.config;

import jakarta.servlet.Filter;
import jakarta.servlet.ServletRegistration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.util.List;

/**
 * SpringMVC 提供的接口，是替代 web.xml 的方案，更方便实现完全注解方式 ssm 处理
 * Springmvc 框架会自动检查当前类的实现类，会自动加载 getRootConfigClasses / getServletConfigClasses 提供的配置类
 *
 * @author lxcecho lxcecho@gmail.com
 * @since 2024/1/1
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 根容器的配置（Spring 的配置文件===Spring 的配置类）
	 *
	 * @return
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{SpringConfig.class};
	}

	/**
	 * web 容器的配置（SpringMVC 的配置文件===SpringMVC 的配置类）
	 *
	 * @return
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{SpringMvcConfig.class};
	}

	/**
	 * 设置 DispatherServlet 对应处理的地址
	 * 一般情况下为 / 代表处理所有请求
	 *
	 * @return
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	/**
	 * 注册拦截器
	 *
	 * @return
	 */
	protected Filter[] getServletFilters() {
		// 添加拦截器，解决乱码问题
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceRequestEncoding(true);
		characterEncodingFilter.setForceResponseEncoding(true);
		return new Filter[]{characterEncodingFilter};
	}

}
