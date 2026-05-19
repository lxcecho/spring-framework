package com.lxcecho.spring.aop.aspect;

import com.lxcecho.spring.aop.intercept.EchoMethodInterceptor;
import com.lxcecho.spring.aop.intercept.EchoMethodInvocation;

import java.lang.reflect.Method;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public class EchoMethodBeforeAdviceInterceptor extends EchoAbstractAspectJAdvice implements EchoMethodInterceptor {

	private EchoJoinPoint jp;

	public EchoMethodBeforeAdviceInterceptor(Object aspect, Method adviceMethod) {
		super(aspect, adviceMethod);
	}

	public void before(Method method, Object[] args, Object target) throws Throwable {
		this.invokeAdviceMethod(this.jp, null, null);
	}

	@Override
	public Object invoke(EchoMethodInvocation mi) throws Throwable {
		jp = mi;
		this.before(mi.getMethod(), mi.getArguments(), mi.getThis());
		return mi.proceed();
	}
}
