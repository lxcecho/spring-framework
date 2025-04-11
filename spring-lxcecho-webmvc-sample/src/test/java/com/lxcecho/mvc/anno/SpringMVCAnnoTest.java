package com.lxcecho.mvc.anno;

import com.lxcecho.mvc.config.WebConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testHello() throws Exception {
		mockMvc.perform(get("/sayHello"))
				.andExpect(status().isOk())
				.andExpect(view().name("success"));
		//System.out.println(wac.getBean("hello"));
	}


}

