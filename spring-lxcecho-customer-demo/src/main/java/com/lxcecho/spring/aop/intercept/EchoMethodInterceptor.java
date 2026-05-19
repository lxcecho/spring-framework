package com.lxcecho.spring.aop.intercept;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public interface EchoMethodInterceptor {

	Object invoke(EchoMethodInvocation invocation) throws Throwable;
}
