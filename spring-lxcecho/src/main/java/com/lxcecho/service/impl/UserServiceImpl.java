package com.lxcecho.service.impl;

import com.lxcecho.processor.EchoValue;
import com.lxcecho.service.OrderService;
import com.lxcecho.spring.*;
import com.lxcecho.dao.UserDao;
import com.lxcecho.service.UserService;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/11
 */
@Bean
@Component
public class UserServiceImpl implements UserService, BeanNameAware {

	@DI
	private UserDao userDao;

	public void add() {
		System.out.println("service.......");
		//调用dao的方法
		userDao.add();
	}

	@Override
	public void test() {
		System.out.println(beanName);
	}

	@Autowired
	private OrderService orderService;

	@EchoValue("xxx")
	private String test;


	private String beanName;

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}
}
