package com.lxcecho.spring.beans.config;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public class EchoBeanDefinition {
	private String factoryBeanName;
	private String beanClassName;

	public String getFactoryBeanName() {
		return factoryBeanName;
	}

	public void setFactoryBeanName(String factoryBeanName) {
		this.factoryBeanName = factoryBeanName;
	}

	public String getBeanClassName() {
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
	}
}
