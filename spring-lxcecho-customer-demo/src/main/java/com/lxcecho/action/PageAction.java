package com.lxcecho.action;

import com.lxcecho.service.QueryService;
import com.lxcecho.spring.annotation.EchoAutowired;
import com.lxcecho.spring.annotation.EchoController;
import com.lxcecho.spring.annotation.EchoRequestMapping;
import com.lxcecho.spring.annotation.EchoRequestParam;
import com.lxcecho.spring.webmvc.servlet.EchoModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 公布接口url
 *
 * @author Tom
 *
 */
@EchoController
@EchoRequestMapping("/")
public class PageAction {

	@EchoAutowired
	QueryService queryService;

	@EchoRequestMapping("/first.html")
	public EchoModelAndView query(@EchoRequestParam("teacher") String teacher) {
		String result = queryService.query(teacher);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("teacher", teacher);
		model.put("data", result);
		model.put("token", "123456");
		return new EchoModelAndView("first.html", model);
	}

}
