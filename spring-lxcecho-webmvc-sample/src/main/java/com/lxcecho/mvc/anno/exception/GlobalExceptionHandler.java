package com.lxcecho.mvc.anno.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器，内部可以定义异常处理 Handler!
 *
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-05-31
 */
@RestControllerAdvice // @RestControllerAdvice = @ControllerAdvice + @ResponseBody，代表当前类的异常处理 controller!
public class GlobalExceptionHandler {
	@ExceptionHandler
	public ModelAndView doException(Exception e) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		modelAndView.addObject("ex", e);
		return modelAndView;
	}
}
