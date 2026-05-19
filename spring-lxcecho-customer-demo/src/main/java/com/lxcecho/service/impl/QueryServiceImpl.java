package com.lxcecho.service.impl;

import com.lxcecho.service.QueryService;
import com.lxcecho.spring.annotation.EchoService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 查询业务
 *
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
@EchoService
public class QueryServiceImpl implements QueryService {

	/**
	 * 查询
	 */
	public String query(String name) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		String json = "{name:\"" + name + "\",time:\"" + time + "\"}";
		System.out.println("这是在业务方法中打印的：" + json);
		return json;
	}

}
