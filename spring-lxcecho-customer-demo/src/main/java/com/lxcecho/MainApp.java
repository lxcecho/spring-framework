package com.lxcecho;

import com.lxcecho.spring.EchoAnnotationApplicationContext;
import com.lxcecho.spring.ApplicationContext;
import com.lxcecho.service.UserService;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/11
 */
public class MainApp {
	public static void main(String[] args) {
		ApplicationContext context = new EchoAnnotationApplicationContext("com.lxcecho");
		UserService userService = (UserService) context.getBean(UserService.class);
		System.out.println(userService);
		userService.add();

		// 扫描--->创建单例 EchoBean EchoBeanDefinition BeanPostProcess
		/*EchoApplicationContext applicationContext = new EchoApplicationContext(AppConfig.class);
		UserService userService = (UserService) applicationContext.getBean("userService");
		userService.test();*/
	}
}