package com.lxcecho.spring;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/11
 */
public interface ApplicationContext {

	Object getBean(Class<?> clazz);

}
