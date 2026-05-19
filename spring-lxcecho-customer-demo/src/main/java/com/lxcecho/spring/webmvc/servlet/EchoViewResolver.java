package com.lxcecho.spring.webmvc.servlet;

import java.io.File;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public class EchoViewResolver {
	private final String DEFAULT_TEMPLATE_SUFFIX = ".html";
	private File tempateRootDir;

	public EchoViewResolver(String templateRoot) {
		String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
		tempateRootDir = new File(templateRootPath);
	}

	public EchoView resolveViewName(String viewName) {
		if (null == viewName || "".equals(viewName.trim())) {
			return null;
		}
		viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFIX) ? viewName : (viewName + DEFAULT_TEMPLATE_SUFFIX);
		File templateFile = new File((tempateRootDir.getPath() + "/" + viewName).replaceAll("/+", "/"));
		return new EchoView(templateFile);
	}

}
