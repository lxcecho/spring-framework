package com.lxcecho.anno.ioc.processor.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

/**
 * 【大坑：postProcessProperties 方法返回 null，会导致 AutowiredAnnotationBeanPostProcessor#postProcessProperties 无法执行，导致属性注入失败】
 *
 * @author lxcecho lxcecho@gmail.com
 * @since 20.06.2021
 */
@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
	/**
	 * Logger available to subclasses.
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	public MyInstantiationAwareBeanPostProcessor() {
		System.out.println("MyInstantiationAwareBeanPostProcessor.......Constructor");
	}

	/**
	 * 初始化之前进行后置处理，Spring 留给我们给这个组件创建对象的回调
	 *
	 * @param beanClass the class of the bean to be instantiated
	 * @param beanName  the name of the bean
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		logger.error("lxcecho: MyInstantiationAwareBeanPostProcessor...postProcessBeforeInstantiation=>" + beanClass + "--" + beanName);
		// 如果我们自己创建了对象返回。Spring 则不会帮我们创建对象，用我们自己创建的对象？ 我们创建的这个对象，Spring 会保存单实例？还是每次 getBean 都调到我们这里创建一个新的？
		/*if(beanClass.isAssignableFrom(Cat.class)) {
			return new Dog();
		}*/
		return null;
	}

	/**
	 * 初始化之后进行处理
	 *
	 * @param bean     the bean instance created, with properties not having been set yet
	 * @param beanName the name of the bean
	 * @return
	 * @throws BeansException
	 */
	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		// 提前改变一些 Spring 不管的 bean 里面的属性
		logger.error("lxcecho: MyInstantiationAwareBeanPostProcessor...postProcessAfterInstantiation=>" + bean + "--" + beanName);
		// 返回 false 则 bean 的赋值全部结束
		return true;
	}

	/**
	 * 解析自定义注解进行属性值注入；pvs 封装了所有的属性信息。
	 *
	 * @param pvs      the property values that the factory is about to apply (never {@code null})
	 * @param bean     the bean instance created, but whose properties have not yet been set
	 * @param beanName the name of the bean
	 * @return
	 * @throws BeansException
	 */
	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
			throws BeansException {
		logger.error("lxcecho: MyInstantiationAwareBeanPostProcessor...postProcessProperties=>" + bean + "--" + beanName);
		return postProcessPropertyValues(pvs, null, bean, beanName);
	}

	public PropertyValues postProcessPropertyValues(
			PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
		logger.error("postProcessPropertyValues:");
		return pvs;
	}
}

