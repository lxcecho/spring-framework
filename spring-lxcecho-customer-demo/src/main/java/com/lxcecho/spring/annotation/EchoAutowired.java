package com.lxcecho.spring.annotation;

import java.lang.annotation.*;

/**
 * 自动注入
 *
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-08-30
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EchoAutowired {
	String value() default "";
}

