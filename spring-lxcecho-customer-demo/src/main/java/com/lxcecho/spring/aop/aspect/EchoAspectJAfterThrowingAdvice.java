package com.lxcecho.spring.aop.aspect;

import com.lxcecho.spring.aop.intercept.EchoMethodInterceptor;
import com.lxcecho.spring.aop.intercept.EchoMethodInvocation;

import java.lang.reflect.Method;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public class EchoAspectJAfterThrowingAdvice extends EchoAbstractAspectJAdvice implements EchoMethodInterceptor {

	private String throwName;

	public EchoAspectJAfterThrowingAdvice(Object aspect, Method adviceMethod) {
		super(aspect, adviceMethod);
	}

	@Override
	public Object invoke(EchoMethodInvocation mi) throws Throwable {
		try {
			return mi.proceed();
		} catch (Throwable ex) {
			invokeAdviceMethod(mi, null, ex.getCause());
			throw ex;
		}
	}

	public void setThrowName(String throwName) {
		this.throwName = throwName;
	}
}
