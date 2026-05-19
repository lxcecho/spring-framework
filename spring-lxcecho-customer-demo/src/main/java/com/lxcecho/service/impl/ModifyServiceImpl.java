package com.lxcecho.service.impl;

import com.lxcecho.service.ModifyService;
import com.lxcecho.spring.annotation.EchoService;

/**
 * 增删改业务
 *
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
@EchoService
public class ModifyServiceImpl implements ModifyService {

	/**
	 * 增加
	 */
	public String add(String name, String addr) throws Exception {
		throw new Exception("这是Tom故意抛出来的异常");

//		return "modifyService add,name=" + name + ",addr=" + addr;
	}

	/**
	 * 修改
	 */
	public String edit(Integer id, String name) {
		return "modifyService edit,id=" + id + ",name=" + name;
	}

	/**
	 * 删除
	 */
	public String remove(Integer id) {
		return "modifyService id=" + id;
	}

}
