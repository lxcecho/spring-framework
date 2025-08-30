package com.lxcecho.anno.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/24
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.lxcecho.aop.annoaop")
public class SpringAopConfig {
}
