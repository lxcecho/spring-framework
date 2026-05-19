package com.lxcecho.spring.webmvc.servlet;

import java.util.Map;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public class EchoModelAndView {
	private String viewName;
	private Map<String, ?> model;

	public EchoModelAndView(String viewName, Map<String, ?> model) {
		this.viewName = viewName;
		this.model = model;
	}

	public EchoModelAndView(String viewName) {
		this.viewName = viewName;
	}

	public String getViewName() {
		return viewName;
	}

	public Map<String, ?> getModel() {
		return model;
	}
}


