package com.xbw.website.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xbw.website.bo.UserBo;


@Controller
@RequestMapping("/")
public class WebController {
	private Logger log = LoggerFactory.getLogger(WebController.class);
	@RequestMapping("/")
	public String index(HttpServletRequest req){
		HttpSession session = req.getSession(true);
		 String user = (String) session.getAttribute("user");
		 if(user==null){
			 return "redirect:/login";
		 }
		return "/index";
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest req){
		HttpSession session = req.getSession(true);
		 String user = (String) session.getAttribute("user");
		 if(user!=null){
			 return "/index";
		 }
		return "/login";
	}
	@RequestMapping("/redirectUrl")
	public String redirectUrl(HttpServletRequest req){
		return "redirect:/";
	}
	@RequestMapping("/auth")
	@ResponseBody
	public Map<String,String> auth(@RequestBody UserBo u,HttpServletRequest req){
		Map<String,String> ret = new HashMap();
		if("xbw".equals( u.getName()) && "1".equals( u.getPassword())){
			 ret.put("code", "1");
			 ret.put("msg", "登录成功！");
		}else{
			 ret.put("code", "0");
			 ret.put("msg", "用户名或密码错误！");
		}
		req.getSession().setAttribute("user", "xbw");
		
		return ret;
	}
}
