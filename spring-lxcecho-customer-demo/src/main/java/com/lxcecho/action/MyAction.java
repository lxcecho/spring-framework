package com.lxcecho.action;

import com.lxcecho.service.ModifyService;
import com.lxcecho.service.QueryService;
import com.lxcecho.spring.annotation.EchoAutowired;
import com.lxcecho.spring.annotation.EchoController;
import com.lxcecho.spring.annotation.EchoRequestMapping;
import com.lxcecho.spring.annotation.EchoRequestParam;
import com.lxcecho.spring.webmvc.servlet.EchoModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 公布接口url
 *
 * @author Tom
 *
 */
@EchoController
@EchoRequestMapping("/web")
public class MyAction {

	@EchoAutowired
	QueryService queryService;
	@EchoAutowired
	ModifyService modifyService;

	@EchoRequestMapping("/query.json")
	public EchoModelAndView query(HttpServletRequest request, HttpServletResponse response,
								  @EchoRequestParam("name") String name) {
		String result = queryService.query(name);
		return out(response, result);
	}

	@EchoRequestMapping("/add*.json")
	public EchoModelAndView add(HttpServletRequest request, HttpServletResponse response,
								@EchoRequestParam("name") String name, @EchoRequestParam("addr") String addr) {
		try {
			String result = modifyService.add(name, addr);
			return out(response, result);
		} catch (Throwable e) {
			Map<String, String> model = new HashMap<String, String>();
			model.put("detail", e.getCause().getMessage());
			model.put("stackTrace", Arrays.toString(e.getStackTrace()));
			return new EchoModelAndView("500", model);
		}
	}

	@EchoRequestMapping("/remove.json")
	public EchoModelAndView remove(HttpServletRequest request, HttpServletResponse response,
								   @EchoRequestParam("id") Integer id) {
		String result = modifyService.remove(id);
		return out(response, result);
	}

	@EchoRequestMapping("/edit.json")
	public EchoModelAndView edit(HttpServletRequest request, HttpServletResponse response,
								 @EchoRequestParam("id") Integer id,
								 @EchoRequestParam("name") String name) {
		String result = modifyService.edit(id, name);
		return out(response, result);
	}


	private EchoModelAndView out(HttpServletResponse resp, String str) {
		try {
			resp.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
