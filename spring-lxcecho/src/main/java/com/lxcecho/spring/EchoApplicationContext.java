package com.lxcecho.spring;

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
	private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
	private Map<String, Object> singletonObjects = new HashMap<>();
	private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

	public EchoApplicationContext(Class<?> configClass) {
		this.configClass = configClass;

		// 扫描
		scan(configClass);

		for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
			String beanName = entry.getKey();
			BeanDefinition beanDefinition = entry.getValue();
			if (beanDefinition.getScope().equals("singleton")) {

				Object bean = createBean(beanName, beanDefinition);
				singletonObjects.put(beanName, bean);

			}
		}

	}

	private Object createBean(String beanName, BeanDefinition beanDefinition) {
		Class<?> clazz = beanDefinition.getType();

		Object instance = null;
		try {

			instance = clazz.getConstructor().newInstance();

			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(Autowired.class)) {

					field.setAccessible(true);

					field.set(instance, getBean(field.getName()));
				}
			}

			if (instance instanceof BeanNameAware) {
				((BeanNameAware) instance).setBeanName(beanName);
			}

			for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
				instance = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
			}

			if (instance instanceof InitializingBean) {
				((InitializingBean) instance).afterPropertiesSet();
			}

			for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
				instance = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
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

		BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

		if (beanDefinition.getScope().equals("singleton")) {
			Object singletonBean = singletonObjects.get(beanName);
			if (singletonBean == null) {
				singletonBean = createBean(beanName, beanDefinition);
				singletonObjects.put(beanName, singletonBean);
			}
			return singletonBean;
		} else {
			// 原型
			return createBean(beanName, beanDefinition);
		}

	}


	private void scan(Class<?> configClass) {
		if (configClass.isAnnotationPresent(ComponentScan.class)) {
			ComponentScan componentScanAnnotation = configClass.getAnnotation(ComponentScan.class);
			String path = componentScanAnnotation.value();
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

						if (clazz.isAnnotationPresent(Component.class)) {

							if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
								BeanPostProcessor instance = (BeanPostProcessor) clazz.getConstructor().newInstance();
								beanPostProcessorList.add(instance);
							}

							Component componentAnnotation = clazz.getAnnotation(Component.class);
							String beanName = componentAnnotation.value();
							if ("".equals(beanName)) {
								beanName = Introspector.decapitalize(clazz.getSimpleName());
							}

							BeanDefinition beanDefinition = new BeanDefinition();
							beanDefinition.setType(clazz);

							if (clazz.isAnnotationPresent(Scope.class)) {
								Scope scopeAnnotation = clazz.getAnnotation(Scope.class);
								String value = scopeAnnotation.value();
								beanDefinition.setScope(value);
							} else {
								beanDefinition.setScope("singleton");
							}

							beanDefinitionMap.put(beanName, beanDefinition);
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
