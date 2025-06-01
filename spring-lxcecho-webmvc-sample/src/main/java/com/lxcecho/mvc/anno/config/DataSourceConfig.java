package com.lxcecho.mvc.anno.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-06-01
 */
@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceConfig {

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Value("${jdbc.driverClassName}")
	private String driverClassName;

	@Bean
	public DataSource dataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(driverClassName);
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		return druidDataSource;
	}
}

