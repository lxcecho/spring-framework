package com.lxcecho.mvc.anno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-04-11
 */
@Controller
public class AnnoController {
	private static final String SUCCESS = "success";

	@RequestMapping(value = "sayHi", method = RequestMethod.GET)
	public String sayHi() {
		System.out.println("sayHi");
		return "success"; // 结果如何跳转呢？需要配置映射解析器
	}
}
