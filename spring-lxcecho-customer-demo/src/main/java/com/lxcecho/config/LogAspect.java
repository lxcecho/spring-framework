package com.lxcecho.config;

import com.lxcecho.spring.aop.aspect.EchoJoinPoint;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public class LogAspect {

	// 在调用一个方法之前，执行 before 方法
	public void before(EchoJoinPoint joinPoint) {
		joinPoint.setUserAttribute("startTime_" + joinPoint.getMethod().getName(), System.currentTimeMillis());
		// 这个方法中的逻辑，是由我们自己写的
		System.out.println("Invoker Before Method!!!");
	}

	// 在调用一个方法之后，执行 after 方法
	public void after(EchoJoinPoint joinPoint) {
		long startTime = (Long) joinPoint.getUserAttribute("startTime_" + joinPoint.getMethod().getName());
		long endTime = System.currentTimeMillis();

		System.out.println("Invoker After Method!!!" + "use time " + (endTime - startTime));
	}

	public void afterThrowing(EchoJoinPoint joinPoint, Throwable ex) {

		System.out.println("出现异常: " + ex.getMessage());
	}
}
