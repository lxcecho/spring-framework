package com.lxcecho.spring.aop.aspect;

import com.lxcecho.spring.aop.intercept.EchoMethodInterceptor;
import com.lxcecho.spring.aop.intercept.EchoMethodInvocation;

import java.lang.reflect.Method;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public class EchoAfterReturningAdviceInterceptor extends EchoAbstractAspectJAdvice implements EchoMethodInterceptor {

	private EchoJoinPoint jp;

	public EchoAfterReturningAdviceInterceptor(Object aspect, Method adviceMethod) {
		super(aspect, adviceMethod);
	}

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		invokeAdviceMethod(this.jp, returnValue, null);
	}

	@Override
	public Object invoke(EchoMethodInvocation mi) throws Throwable {
		this.jp = mi;
		Object returnValue = mi.proceed();
		this.afterReturning(returnValue, mi.getMethod(), mi.getArguments(), mi.getThis());
		return returnValue;
	}
}
