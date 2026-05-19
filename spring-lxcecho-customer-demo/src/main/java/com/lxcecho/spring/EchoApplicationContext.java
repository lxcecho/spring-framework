package com.lxcecho.spring;

import com.lxcecho.spring.annotation.EchoAutowired;
import com.lxcecho.spring.annotation.EchoComponent;
import com.lxcecho.spring.annotation.EchoComponentScan;
import com.lxcecho.spring.annotation.EchoScope;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-08-30
 */
public class EchoApplicationContext {

	private Class<?> configClass;
	private Map<String, EchoBeanDefinition> beanDefinitionMap = new HashMap<>();
	private Map<String, Object> singletonObjects = new HashMap<>();
	private List<EchoBeanPostProcessor> echoBeanPostProcessorList = new ArrayList<>();

	public EchoApplicationContext(Class<?> configClass) {
		this.configClass = configClass;

		// 扫描
		scan(configClass);

		for (Map.Entry<String, EchoBeanDefinition> entry : beanDefinitionMap.entrySet()) {
			String beanName = entry.getKey();
			EchoBeanDefinition echoBeanDefinition = entry.getValue();
			if (echoBeanDefinition.getScope().equals("singleton")) {

				Object bean = createBean(beanName, echoBeanDefinition);
				singletonObjects.put(beanName, bean);

			}
		}

	}

	private Object createBean(String beanName, EchoBeanDefinition echoBeanDefinition) {
		Class<?> clazz = echoBeanDefinition.getType();

		Object instance = null;
		try {

			instance = clazz.getConstructor().newInstance();

			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(EchoAutowired.class)) {

					field.setAccessible(true);

					field.set(instance, getBean(field.getName()));
				}
			}

			if (instance instanceof EchoBeanNameAware) {
				((EchoBeanNameAware) instance).setBeanName(beanName);
			}

			for (EchoBeanPostProcessor echoBeanPostProcessor : echoBeanPostProcessorList) {
				instance = echoBeanPostProcessor.postProcessBeforeInitialization(instance, beanName);
			}

			if (instance instanceof EchoInitializingBean) {
				((EchoInitializingBean) instance).afterPropertiesSet();
			}

			for (EchoBeanPostProcessor echoBeanPostProcessor : echoBeanPostProcessorList) {
				instance = echoBeanPostProcessor.postProcessAfterInitialization(instance, beanName);
			}


		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}

		return instance;
	}


	public Object getBean(String beanName) {

		if (!beanDefinitionMap.containsKey(beanName)) {
			throw new NullPointerException();
		}

		EchoBeanDefinition echoBeanDefinition = beanDefinitionMap.get(beanName);

		if (echoBeanDefinition.getScope().equals("singleton")) {
			Object singletonBean = singletonObjects.get(beanName);
			if (singletonBean == null) {
				singletonBean = createBean(beanName, echoBeanDefinition);
				singletonObjects.put(beanName, singletonBean);
			}
			return singletonBean;
		} else {
			// 原型
			return createBean(beanName, echoBeanDefinition);
		}

	}


	private void scan(Class<?> configClass) {
		if (configClass.isAnnotationPresent(EchoComponentScan.class)) {
			EchoComponentScan echoComponentScanAnnotation = configClass.getAnnotation(EchoComponentScan.class);
			String path = echoComponentScanAnnotation.value();
			path = path.replace(".", "/");  //     com/lxcecho/service

			ClassLoader classLoader = EchoApplicationContext.class.getClassLoader();
			URL resource = classLoader.getResource(path);
			assert resource != null;
			File file = new File(resource.getFile());

			if (file.isDirectory()) {
				for (File f : Objects.requireNonNull(file.listFiles())) {
					String absolutePath = f.getAbsolutePath();

					absolutePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
					absolutePath = absolutePath.replace("\\", ".");


					try {
						Class<?> clazz = classLoader.loadClass(absolutePath);

						if (clazz.isAnnotationPresent(EchoComponent.class)) {

							if (EchoBeanPostProcessor.class.isAssignableFrom(clazz)) {
								EchoBeanPostProcessor instance = (EchoBeanPostProcessor) clazz.getConstructor().newInstance();
								echoBeanPostProcessorList.add(instance);
							}

							EchoComponent echoComponentAnnotation = clazz.getAnnotation(EchoComponent.class);
							String beanName = echoComponentAnnotation.value();
							if ("".equals(beanName)) {
								beanName = Introspector.decapitalize(clazz.getSimpleName());
							}

							EchoBeanDefinition echoBeanDefinition = new EchoBeanDefinition();
							echoBeanDefinition.setType(clazz);

							if (clazz.isAnnotationPresent(EchoScope.class)) {
								EchoScope echoScopeAnnotation = clazz.getAnnotation(EchoScope.class);
								String value = echoScopeAnnotation.value();
								echoBeanDefinition.setScope(value);
							} else {
								echoBeanDefinition.setScope("singleton");
							}

							beanDefinitionMap.put(beanName, echoBeanDefinition);
						}
					} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
					         InvocationTargetException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
