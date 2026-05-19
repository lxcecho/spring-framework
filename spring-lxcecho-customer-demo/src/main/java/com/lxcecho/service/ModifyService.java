package com.lxcecho.service;

/**
 * 增删改业务
 *
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public interface ModifyService {

	/**
	 * 增加
	 */
	public String add(String name, String addr) throws Exception;

	/**
	 * 修改
	 */
	public String edit(Integer id, String name);

	/**
	 * 删除
	 */
	public String remove(Integer id);

}
