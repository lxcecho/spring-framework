package com.lxcecho.processor;

import com.lxcecho.spring.BeanPostProcessor;
import com.lxcecho.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-08-30
 */
@Component
public class EchoBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) {

		if (beanName.equals("userService")) {
			Object proxyInstance = Proxy.newProxyInstance(EchoBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
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
