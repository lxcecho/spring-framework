package com.lxcecho.anno.ioc.controller;

import com.lxcecho.anno.ioc.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/10
 */
@Controller
public class ResourceUserController {

	/**
	 * 根据名称进行注入
	 */
    @Resource(name = "myUserService")
	private UserService userService;

    // 根据类型配置
    /*@Resource
    private UserService userService;*/

    public void add() {
        System.out.println("ResourceUserController........");
        userService.add();
    }
}
