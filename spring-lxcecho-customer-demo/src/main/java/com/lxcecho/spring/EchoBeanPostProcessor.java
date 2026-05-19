package com.lxcecho.spring;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-08-30
 */
public interface EchoBeanPostProcessor {

	default Object postProcessBeforeInitialization(Object bean, String beanName) {
		return bean;
	}

	default Object postProcessAfterInitialization(Object bean, String beanName) {
		return bean;
	}

}
