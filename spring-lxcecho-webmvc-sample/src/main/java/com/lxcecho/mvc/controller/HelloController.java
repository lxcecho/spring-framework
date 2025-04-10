package com.lxcecho.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-04-09
 */
@Controller
public class HelloController {

	/**
	 * 映射请求的名称：用于客户端请求；类似 Struts2 中 action 映射配置的 action 名称
	 * 1. 使用 @RequestMapping 注解来映射请求的 URL
	 * 2. 返回值会通过视图解析器解析为实际的物理视图, 对于 InternalResourceViewResolver 视图解析器,
	 * 会做如下的解析:
	 * 通过 prefix + returnVal + suffix 这样的方式得到实际的物理视图, 然后做转发操作.
	 * /WEB-INF/views/success.jsp
	 */
	@RequestMapping(value = "sayHello", method = RequestMethod.GET)
	public String sayHello() {
		System.out.println("sayHello");
		return "success"; // 结果如何跳转呢？需要配置映射解析器
	}

}
