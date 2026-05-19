package com.lxcecho.spring.webmvc.servlet;

import java.util.regex.Pattern;

import java.lang.reflect.Method;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public class EchoHandlerMapping {
	private Pattern pattern; // URL
	private Method method; // 对应的 Method
	private Object controller; // Method 对应的实例对象

	public EchoHandlerMapping(Pattern pattern, Object controller, Method method) {
		this.pattern = pattern;
		this.method = method;
		this.controller = controller;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Object getController() {
		return controller;
	}

	public void setController(Object controller) {
		this.controller = controller;
	}
}
