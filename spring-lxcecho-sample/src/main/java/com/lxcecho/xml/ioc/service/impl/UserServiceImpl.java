package com.lxcecho.xml.ioc.service.impl;

import com.lxcecho.xml.ioc.dao.UserDao;
import com.lxcecho.xml.ioc.service.UserService;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/10
 */
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void addUserService() {
		System.out.println("userService 方法执行了...");
		userDao.addUserDao();
//        UserDao userDao = new UserDaoImpl();
//        userDao.addUserDao();
	}

}
