package com.lxcecho.processor;

import com.lxcecho.spring.EchoBeanPostProcessor;
import com.lxcecho.spring.annotation.EchoComponent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-08-30
 */
@EchoComponent
public class EchoBeanPostProcessorImpl implements EchoBeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) {

		if (beanName.equals("userService")) {
			Object proxyInstance = Proxy.newProxyInstance(EchoBeanPostProcessorImpl.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					// 切面
					System.out.println("切面逻辑");
					return method.invoke(bean, args);
				}
			});

			return proxyInstance;
		}

		// bean
		return bean;
	}
}
