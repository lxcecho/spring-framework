package com.lxcecho.spring.webmvc.servlet;

import com.lxcecho.spring.annotation.EchoController;
import com.lxcecho.spring.annotation.EchoRequestMapping;
import com.lxcecho.spring.context.EchoApplicationContext;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;

/**
 * 委派模式
 * 职责：负责任务调度，请求分发
 *
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public class EchoDispatcherServlet extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;

	private transient  EchoApplicationContext applicationContext;

	private transient  List<EchoHandlerMapping> handlerMappings = new ArrayList<>();

	private transient  Map<EchoHandlerMapping, EchoHandlerAdapter> handlerAdapters = new HashMap<>();

	private transient  List<EchoViewResolver> viewResolvers = new ArrayList<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 6、委派,根据 URL 去找到一个对应的 Method 并通过 response 返回
		try {
			doDispatch(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				processDispatchResult(req, resp, new EchoModelAndView("500"));
			} catch (Exception e1) {
				e1.printStackTrace();
				resp.getWriter().write("500 Exception,Detail : " + Arrays.toString(e.getStackTrace()));
			}
		}

	}

	private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 完成了对 HandlerMapping 的封装
		// 完成了对方法返回值的封装 ModelAndView

		// 1、通过 URL 获得一个 HandlerMapping
		EchoHandlerMapping handler = getHandler(req);
		if (handler == null) {
			processDispatchResult(req, resp, new EchoModelAndView("404"));
			return;
		}

		// 2、根据一个 HandlerMapping 获得一个 HandlerAdapter
		EchoHandlerAdapter ha = getHandlerAdapter(handler);

		// 3、解析某一个方法的形参和返回值之后，统一封装为 ModelAndView 对象
		EchoModelAndView mv = ha.handler(req, resp, handler);

		// 就把 ModelAndView 变成一个 ViewResolver
		processDispatchResult(req, resp, mv);

	}

	private EchoHandlerAdapter getHandlerAdapter(EchoHandlerMapping handler) {
		if (this.handlerAdapters.isEmpty()) {
			return null;
		}
		return this.handlerAdapters.get(handler);
	}

	private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, EchoModelAndView mv) throws Exception {
		if (null == mv) {
			return;
		}
		if (this.viewResolvers.isEmpty()) {
			return;
		}

		for (EchoViewResolver viewResolver : this.viewResolvers) {
			EchoView view = viewResolver.resolveViewName(mv.getViewName());
			// 直接往浏览器输出
//			view.render(mv.getModel(), req, resp); // TODO 因为报错暂时注释掉了
			return;
		}
	}

	private EchoHandlerMapping getHandler(HttpServletRequest req) {
		if (this.handlerMappings.isEmpty()) {
			return null;
		}
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		url = url.replaceAll(contextPath, "").replaceAll("/+", "/");

		for (EchoHandlerMapping mapping : handlerMappings) {
			Matcher matcher = mapping.getPattern().matcher(url);
			if (!matcher.matches()) {
				continue;
			}
			return mapping;
		}
		return null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		// 初始化 Spring 核心 IoC 容器
		applicationContext = new EchoApplicationContext(config.getInitParameter("contextConfigLocation"));

		// 完成了 IoC、DI 和 MVC 部分对接

		// 初始化九大组件
		initStrategies(applicationContext);

		System.out.println("Echo Spring framework is init.");
	}

	private void initStrategies(EchoApplicationContext context) {
//        // 多文件上传的组件
//        initMultipartResolver(context);
//        // 初始化本地语言环境
//        initLocaleResolver(context);
//        // 初始化模板处理器
//        initThemeResolver(context);
		//handlerMapping
		initHandlerMappings(context);
		// 初始化参数适配器
		initHandlerAdapters(context);
//        // 初始化异常拦截器
//        initHandlerExceptionResolvers(context);
//        // 初始化视图预处理器
//        initRequestToViewNameTranslator(context);
		// 初始化视图转换器
		initViewResolvers(context);
//        // FlashMap 管理器
//        initFlashMapManager(context);
	}

	private void initViewResolvers(EchoApplicationContext context) {
		String templateRoot = context.getConfig().getProperty("templateRoot");
		String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

		File templateRootDir = new File(templateRootPath);
		for (File file : templateRootDir.listFiles()) {
			this.viewResolvers.add(new EchoViewResolver(templateRoot));
		}

	}

	private void initHandlerAdapters(EchoApplicationContext context) {
		for (EchoHandlerMapping handlerMapping : handlerMappings) {
			this.handlerAdapters.put(handlerMapping, new EchoHandlerAdapter());
		}
	}

	private void initHandlerMappings(EchoApplicationContext context) {
		if (this.applicationContext.getBeanDefinitionCount() == 0) {
			return;
		}

		for (String beanName : this.applicationContext.getBeanDefinitionNames()) {
			Object instance = applicationContext.getBean(beanName);
			Class<?> clazz = instance.getClass();

			if (!clazz.isAnnotationPresent(EchoController.class)) {
				continue;
			}

			// 相当于提取 class 上配置的 url
			String baseUrl = "";
			if (clazz.isAnnotationPresent(EchoRequestMapping.class)) {
				EchoRequestMapping requestMapping = clazz.getAnnotation(EchoRequestMapping.class);
				baseUrl = requestMapping.value();
			}

			// 只获取 public 的方法
			for (Method method : clazz.getMethods()) {
				if (!method.isAnnotationPresent(EchoRequestMapping.class)) {
					continue;
				}
				// 提取每个方法上面配置的 url
				EchoRequestMapping requestMapping = method.getAnnotation(EchoRequestMapping.class);

				// //demo//query
				String regex = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("\\*", ".*")).replaceAll("/+", "/");
				Pattern pattern = Pattern.compile(regex);
				//handlerMapping.put(url,method);
				handlerMappings.add(new EchoHandlerMapping(pattern, instance, method));
				System.out.println("Mapped : " + regex + "," + method);
			}
		}
	}

}