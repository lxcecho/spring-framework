package com.lxcecho.xml.ioc.dao.impl;

import com.lxcecho.xml.ioc.dao.UserDao;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/10
 */
public class PersonDaoImpl implements UserDao {

	@Override
	public void run() {
		System.out.println("person run....");
	}

	@Override
	public void addUserDao() {
		System.out.println("userDao 方法执行了...");
	}

}
