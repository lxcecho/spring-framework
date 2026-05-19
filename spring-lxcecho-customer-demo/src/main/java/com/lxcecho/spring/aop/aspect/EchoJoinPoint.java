package com.lxcecho.spring.aop.aspect;

import java.lang.reflect.Method;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public interface EchoJoinPoint {
	Method getMethod();

	Object[] getArguments();

	Object getThis();

	void setUserAttribute(String key, Object value);

	Object getUserAttribute(String key);
}
