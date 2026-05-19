package com.lxcecho.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求参数映射
 *
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EchoRequestParam {

	String value() default "";

	boolean required() default true;

}
