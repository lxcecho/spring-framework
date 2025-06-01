package com.lxcecho.mvc.anno.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * Spring MVC 只扫描 controller 组件，可以不指定父容器类，让 MVC 扫所有。@Component+@RequestMapping 就生效了
 *
 * @author lxcecho lxcecho@gmail.com
 * @since 2024/1/1
 */
@ComponentScan(value = "com.lxcecho.mvc.anno", includeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)
}, useDefaultFilters = false)
public class SpringMvcConfig {
	// SpringMVC 的子容器，能扫描的 Spring 容器中的组件
}
