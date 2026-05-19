package com.lxcecho.spring.core;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public interface EchoBeanFactory {
    Object getBean(Class<?> beanClass);

    Object getBean(String beanName);
}
