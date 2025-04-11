package com.lxcecho.mvc.controller;

import com.lxcecho.mvc.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-04-11
 */
@SessionAttributes(value = {"user"}, types = {String.class}) // value 存放的是 String[] 数组，types 是 class[] 数组
@Controller
@RequestMapping("springmvc")
public class SpringMvcController {
	private static final String SUCCESS = "success";

	/**
	 * @SessionAttributes 除了可以通过属性名指定需要放到会话中的属性外（实际上使用的是 value 属性值），
	 * 还可以通过磨西康属性的对象类型指定那些模型属性需要放到会话中（实际上使用的是 types 属性值）
	 * <p>
	 * 注意：该注解只能放在类上面，而不能修饰方法。
	 */
	@RequestMapping("testSessionAttributes")
	public String testSessionAttributes(Map<String, Object> map) {
		User user = new User("tom", "123456", "tom@163.com", 15);
		map.put("user", user);
		map.put("school", "Xian");
		return SUCCESS;
	}

	/**
	 * 目标方法可以添加 Map 类型（实际上也可以是 Model 类型或者 ModelMap 类型）的参数
	 */
	@RequestMapping("testMap")
	public String testMap(Map<String, Object> map) {
		System.out.println(map.getClass().getName());
		map.put("names", Arrays.asList("Tom", "Marry", "Mike"));
		return SUCCESS;
	}

	/**
	 * 目标方法返回值可以是 ModelAndView 类型。
	 * 其中可以包含视图和模型信息.
	 * SpringMVC 会把 ModelAndView 的 model 中的数据方法放入到 request 域对象中。
	 */
	@RequestMapping("testModelAndView")
	public ModelAndView testModelAndView() {
		String view = SUCCESS;
		ModelAndView mav = new ModelAndView(view);
		// 添加模型数据添加到 ModelAndView 中
		mav.addObject("time", new Date());
		return mav;
	}

	/*
	 * 可以使用 Servlet 原生的 API 作为目标方法的参数 具体支持以下类型：
	 * HttPServletRequest
	 * HttpServletResponse
	 * HttpSession
	 * java.security.Principle
	 * Locale InputStream
	 * OutPutStream
	 * Reader
	 * Writer
	 */
	@RequestMapping("testServletAPI")
	public void testServletAPI(HttpServletRequest request,
	                           HttpServletResponse response, Writer out) throws Exception {
		System.out.println("testServletAPI:request=" + request + ",response=" + response);
		out.write("hello world");
		//		return SUCCESS;
	}

	/**
	 * 常用：使用实体对象 POJO 接收请求参数的值
	 * Spring MVC:会按请求参数名和 POJO 属性名进行自动匹配，自动为该对象填充属性值。
	 * 支持级联属性。如：dept.deptId、dept.address.tel等。
	 */
	//POJO------------------500
	@RequestMapping("testPojo")
	// 使用实体对象 POJO 接收请求参数值（form 表单中提交的数据）
	public String testPojo(User user) {
		System.out.println("testPojo:" + user);
		return SUCCESS;
	}

	/*
	 * 了解：
	 * @CookieValue: 映射一个 cookie 值，属性同 @RequestParam
	 */
	@RequestMapping("testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String sessionId) {
		System.out.println("testCookieValue:sessionId = " + sessionId);
		return SUCCESS;
	}

	/*
	 * 了解：映射请求头信息 @RequestHeader，用法同 @RequestParam。
	 */
	@RequestMapping("testRequestHeader")
	public String testRequestHeader(@RequestHeader(value = "Accept-Language") String al) {
		System.out.println("testRequestHeader,Accept-Language:" + al);
		return SUCCESS;
	}

	/*
	 * @RequestParam: 映射请求参数；
	 * value 值即请求参数的参数名；
	 * required 该参数是否必须，默认为 true(若为 true，则参数必须存在，否则出现异常；若不想它出现异常，
	 * 则使该属性的 required=false)；
	 * defaultValue: 请求参数的默认值。(如果前端页面没有携带参数，则可以设置该参数的默认值)
	 */
	@RequestMapping("testRequestParam")
	public String testRequestParam(@RequestParam(value = "username") String username,
	                               @RequestParam(value = "age", defaultValue = "22") Integer age) {
		System.out.println("testRequestParam：username=" + username + ",age=" + age);
		return SUCCESS;
	}

	/**
	 * Rest 风格的 URL：
	 * 以 CRUD 为例：
	 * 新增：/order POST
	 * 修改：/order/1 PUT update?id=1
	 * 获取：/order/1 GET get?id=1
	 * 删除：order/1 DELETE delete?1
	 * <p>
	 * 如何发送 PUT 请求和 DELETE 请求
	 * 1.配置 HiddenHTTPMethodFilter
	 * 2.需要发送 POST 请求
	 * 3.需要在发送 POST 请求时携带一个 name="_method" 的隐藏域，值为 DELETE 或 PUT
	 * <p>
	 * 在 SpringMVC 的目标方法中如何获取 id 呢？
	 * 使用 @PathVariable 注解。
	 */
	//PUT---------------405
	@RequestMapping(value = "testRestPut/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public String testRestPut(@PathVariable Integer id) {
		System.out.println("testRest Put: " + id);
		return SUCCESS;
	}

	// DELETE-------------405
	@RequestMapping(value = "testRestDelete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String testRestDelete(@PathVariable Integer id) {
		System.out.println("testRest Delete: " + id);
		return SUCCESS;
	}

	// POST
	@RequestMapping(value = "testRestPost/{id}", method = RequestMethod.POST)
	public String testRestPost(@PathVariable Integer id) {
		System.out.println("testRest POST " + id);
		return SUCCESS;
	}

	// GET
	@RequestMapping(value = "testRest/{id}", method = RequestMethod.GET)
	public String testRest(@PathVariable("id") Integer id) {
		System.out.println("testRest GET " + id);
		return SUCCESS;
	}

	/*
	 * @PathVariable 可以来映射 URL 中的占位符到目标参数中。
	 */
	@RequestMapping("testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println("testPathVariable..." + id);
		return SUCCESS;
	}

	// 使用通配符
	@RequestMapping("testAntPath/*/abc")
	public String testAntPath() {
		System.out.println("testAntPath...");
		return SUCCESS;
	}

	/*
	 * 了解：可以使用 params 和 headers 来更加精确的映射请求，params 和 headers 支持简单的表达式。
	 */
	@RequestMapping(value = "testParams", params = {"username", "age!=10"},
			headers = {"Accept-Language=zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2"})
	public String testParams() {
		System.out.println("testParams...");
		return SUCCESS;
	}

	/*
	 * 1.RequestMapping 除了可以修饰方法，还可以修饰类；
	 * 2.---类定义处：提供初步的请求映射信息，相对于 WEB 应用的根目录；
	 *   ---方法处：提供进一步的细分映射信息，相对于类定义处的 URL。
	 *   	若类定义处未标注 @RequestMapping，则方法处标记的 URL 相对于 WEB 的应用根目录。
	 */

	@RequestMapping("testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}

	// 常用：使用 method 属性来指定请求方式。默认是 GET 方式提交
	@RequestMapping(value = "testMethod", method = RequestMethod.GET)
	public String testMethod() {
		System.out.println("test Method...");
		return SUCCESS;
	}
}
