package com.lxcecho.spring.aop;

import com.lxcecho.spring.aop.support.EchoAdvisedSupport;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public class EchoDefaultAopProxyFactory {
	public EchoAopProxy createAopProxy(EchoAdvisedSupport config){
		Class<?> targetClass = config.getTargetClass();
		if(targetClass.getInterfaces().length > 0){
			return new EchoJdkDynamicAopProxy(config);
		}
		return new EchoCglibAopProxy();
	}
}
