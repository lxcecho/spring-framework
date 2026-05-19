package com.lxcecho.spring.beans;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public class EchoBeanWrapper {
	private Object wrapperInstance;
	private Class<?> wrappedClass;

	public EchoBeanWrapper(Object instance) {
		this.wrapperInstance = instance;
		this.wrappedClass = instance.getClass();
	}

	public Object getWrapperInstance() {
		return wrapperInstance;
	}

	public Class<?> getWrappedClass() {
		return wrappedClass;
	}
}
