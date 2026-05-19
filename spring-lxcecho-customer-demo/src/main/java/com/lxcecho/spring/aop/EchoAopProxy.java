package com.lxcecho.spring.aop;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public interface EchoAopProxy {

	Object getProxy();

	Object getProxy(ClassLoader classLoader);

}
