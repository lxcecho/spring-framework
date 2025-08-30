package com.lxcecho.anno.ioc.processor.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * BeanFactory 的后置处理器
 *
 * @author lxcecho lxcecho@gmail.com
 * @since 20.06.2021
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	public MyBeanFactoryPostProcessor() {
		System.out.println("MyBeanFactoryPostProcessor.......Constructor");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("BeanFactoryPostProcessor....postProcessBeanFactory==>" + beanFactory);
		int count = beanFactory.getBeanDefinitionCount();
		String[] names = beanFactory.getBeanDefinitionNames();
		System.out.println("当前 BeanFactory 中有 " + count + " 个 Bean");
		System.out.println(Arrays.asList(names));
	}
}
