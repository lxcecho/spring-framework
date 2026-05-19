package com.lxcecho.spring.aop;

import com.lxcecho.spring.aop.intercept.EchoMethodInvocation;
import com.lxcecho.spring.aop.support.EchoAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public class EchoJdkDynamicAopProxy implements EchoAopProxy, InvocationHandler {
	private EchoAdvisedSupport advised;

	public EchoJdkDynamicAopProxy(EchoAdvisedSupport config) {
		this.advised = config;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, this.advised.getTargetClass());

		EchoMethodInvocation invocation = new EchoMethodInvocation(proxy, this.advised.getTarget(), method, args, this.advised.getTargetClass(), chain);

		return invocation.proceed();
	}

	public Object getProxy() {
		return getProxy(this.getClass().getClassLoader());
	}

	@Override
	public Object getProxy(ClassLoader classLoader) {
		return Proxy.newProxyInstance(classLoader, this.advised.getTargetClass().getInterfaces(), this);
	}
}