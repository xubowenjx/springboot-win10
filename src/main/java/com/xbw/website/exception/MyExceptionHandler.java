package com.xbw.website.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xbw.website.utils.ResultUtil;

/**
 * @ClassName: MyExceptionHandler
 * @Description: 全局异常处理
 * @author: xubowen
 * @date: 2018年4月22日 下午3:06:11
 *
 */
@ControllerAdvice
public class MyExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	public Object errorHandler(HttpServletRequest reqest, HttpServletResponse response, Exception e) throws Exception {
		if (isAjax(reqest)) {// 异步的异常处理
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-type", "application/json;charset=UTF-8");
			response.setStatus(200);
			response.getWriter().write(JSON.toJSONString(ResultUtil.error(e.getMessage())));
			return null;
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("error");
		String reqestPath = reqest.getRequestURI();
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", reqestPath);
		map.put("msg", e.getMessage());
		model.addObject("error", map);
		return model;
	}

	/**
	 * @Title: isAjax
	 * @Description: 判断是否是ajax请求
	 * @Author: xubowen
	 * @Create Date: 2018年4月22日 下午1:58:54
	 * @History: 2018年4月22日 下午1:58:54 xubowen Created.
	 *
	 * @param reqest
	 * @return
	 *
	 */
	public static boolean isAjax(HttpServletRequest reqest) {
		String header = reqest.getHeader("X-Requested-With");
		return (header != null && "XMLHttpRequest".equals(header));
	}
}
