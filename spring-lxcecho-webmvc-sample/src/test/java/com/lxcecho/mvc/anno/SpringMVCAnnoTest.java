package com.lxcecho.mvc.anno;

import com.lxcecho.mvc.anno.config.WebConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-04-10
 */
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
@ExtendWith(SpringExtension.class) // 使用 SpringExtension
public class SpringMVCAnnoTest {

	/**
	 * 使用 MockMvc 来模拟请求
	 */
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testHello() throws Exception {
		// 发送 GET 请求并验证返回的视图名称
		mockMvc.perform(get("/sayHi").accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("success"));
		// System.out.println(wac.getBean("hello"));
	}

}