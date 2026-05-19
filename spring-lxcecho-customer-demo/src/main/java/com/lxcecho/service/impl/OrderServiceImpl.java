package com.lxcecho.service.impl;

import com.lxcecho.service.OrderService;
import com.lxcecho.spring.annotation.EchoComponent;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-08-30
 */
@EchoComponent
public class OrderServiceImpl implements OrderService {
	@Override
	public void test() {
		System.out.println("test");
	}
}
