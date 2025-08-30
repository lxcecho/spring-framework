package com.lxcecho.anno.ioc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/10
 */
@Configuration // 配置类
@ComponentScan("com.lxcecho.ioc.iocanno") // 开启组件扫描
public class SpringConfig {
}
