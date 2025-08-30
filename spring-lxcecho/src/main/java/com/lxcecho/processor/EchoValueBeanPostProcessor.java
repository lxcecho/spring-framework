package com.lxcecho.processor;

import com.lxcecho.spring.BeanPostProcessor;
import com.lxcecho.spring.Component;

import java.lang.reflect.Field;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-08-30
 */
@Component
public class EchoValueBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) {

		for (Field field : bean.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(EchoValue.class)) {
				field.setAccessible(true);
				try {
					field.set(bean, field.getAnnotation(EchoValue.class).value());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		// bean
		return bean;
	}

}