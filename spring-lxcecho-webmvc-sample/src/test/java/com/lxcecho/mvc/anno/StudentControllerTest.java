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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-06-01
 */
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
@ExtendWith(SpringExtension.class) // 使用 SpringExtension
public class StudentControllerTest {

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

	/**
	 * 测试获取所有学生信息
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetStudents() throws Exception {
		mockMvc.perform(get("/findAllStudents").accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	/**
	 * 测试创建新学生记录
	 *
	 * @throws Exception
	 */
	@Test
	public void testCreateStudent() throws Exception {
		// 创建一个学生对象作为请求体
		String studentJson = "{\"name\":\"张三\", \"age\":20, \"email\":\"zhangsanfengA@gmail.com\"}";

		mockMvc.perform(post("/createStudent")
						.contentType(MediaType.APPLICATION_JSON)
						.content(studentJson))
				.andDo(print())
				.andExpect(status().isOk());
	}

	/**
	 * 测试更新学生信息
	 *
	 * @throws Exception
	 */
	@Test
	public void testUpdateStudent() throws Exception {
		// 假设已有学生ID为1，先获取其信息
		String updatedStudentJson = "{\"id\":10, \"name\":\"李四\", \"age\":21, \"email\":\"lisi@example.com\"}";

		mockMvc.perform(put("/updateStudent")
						.contentType(MediaType.APPLICATION_JSON)
						.content(updatedStudentJson))
				.andDo(print())
				.andExpect(status().isOk());
	}

	/**
	 * 测试删除学生记录
	 *
	 * @throws Exception
	 */
	@Test
	public void testDeleteStudent() throws Exception {
		Long studentId = 6L; // 假设要删除的学生ID为1

		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteStudent/" + studentId))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
