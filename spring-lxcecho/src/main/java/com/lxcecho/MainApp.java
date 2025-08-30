package com.lxcecho;

import com.lxcecho.config.AppConfig;
import com.lxcecho.spring.AnnotationApplicationContext;
import com.lxcecho.spring.ApplicationContext;
import com.lxcecho.service.UserService;
import com.lxcecho.spring.EchoApplicationContext;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/11
 */
public class MainApp {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationApplicationContext("com.lxcecho");
		UserService userService = (UserService) context.getBean(UserService.class);
		System.out.println(userService);
		userService.add();

		// 扫描--->创建单例 Bean BeanDefinition BeanPostProcess
		/*EchoApplicationContext applicationContext = new EchoApplicationContext(AppConfig.class);
		UserService userService = (UserService) applicationContext.getBean("userService");
		userService.test();*/
	}
}