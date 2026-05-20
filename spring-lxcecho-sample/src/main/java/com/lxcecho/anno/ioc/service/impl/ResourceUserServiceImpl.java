package com.lxcecho.anno.ioc.service.impl;

import com.lxcecho.anno.ioc.dao.UserDao;
import com.lxcecho.anno.ioc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/10
 */
@Service("myUserService")
public class ResourceUserServiceImpl implements UserService {

	/**
	 * 不 指定名称，根据属性名称进行注入
	 */
	@Autowired
	private UserDao userDao;

	@Override
	public void add() {
		System.out.println("ResourceUserServiceImpl.....");
		userDao.add();
	}
}
