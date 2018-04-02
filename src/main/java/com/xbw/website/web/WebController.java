package com.xbw.website.web;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.xbw.website.bo.UserBo;

@Controller
@RequestMapping("/")
public class WebController {
	private Logger log = LoggerFactory.getLogger(WebController.class);
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping("/")
	public String index(HttpServletRequest req) {
		Locale locale = LocaleContextHolder.getLocale();
		String query = messageSource.getMessage("public.btn.query", null, locale);
		log.info("query:"+query);
		HttpSession session = req.getSession(true);
		String user = (String) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}
		return "/index";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		String user = (String) session.getAttribute("user");
		String i18n =  session.getAttribute("i18n").toString();
		String show ="English";
		if(i18n.equals("en_US")){
			show = "中文";
			i18n = "zh_CN";
		}else{
			i18n = "en_US";
		}
		req.setAttribute("lang", i18n);
		req.setAttribute("show", show);
		if (user != null) {
			return "redirect:/";
		}
		return "/login";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		log.info("退出系统");
		return "/login";
	}
	/**
	 * @Title: changeLang
	 * @Description: 切换语言
	 * @Author: xubowen              
	 * @Create Date: 2018年3月29日 下午9:44:46
	 * @History: 2018年3月29日 下午9:44:46 xubowen Created.
	 *
	 * @param lang
	 * @return
	 *
	 */
	@RequestMapping("/changlang")
	@ResponseBody
	public String changeLang(HttpServletRequest req){
		 String lang = req.getParameter("lang");
		String[] strs = lang.split("_");
		Locale locale = new Locale(strs[0], strs[1]);
		LocaleContextHolder.setLocale(locale);
		HttpSession session = req.getSession();
		session.setAttribute("i18n", locale);
		return lang;
	}

	@RequestMapping("/redirectUrl")
	public String redirectUrl(HttpServletRequest req) {
		return "redirect:/";
	}

	@RequestMapping("/auth")
	@ResponseBody
	public Map<String, String> auth(@RequestBody UserBo u, HttpServletRequest req) {
		Map<String, String> ret = new HashMap();
		if ("xbw".equals(u.getUserName()) && "1".equals(u.getUserPassword())) {
			ret.put("code", "1");
			ret.put("msg", "登录成功！");
		} else {
			ret.put("code", "0");
			ret.put("msg", "用户名或密码错误！");
		}
		req.getSession().setAttribute("user", "xbw");

		return ret;
	}

	@Cacheable(value = "todayImgae", key = "#date")
	@RequestMapping("/todayImgae")
	@ResponseBody
	public String todayImgae(String date) {
		String host = "http://cn.bing.com";
		log.info("执行请求" + date);
		String url = host + "/HPImageArchive.aspx?format=js&idx=0&n=1";
		JSONObject json = restTemplate.getForEntity(url, JSONObject.class).getBody();
		String str = json.getJSONArray("images").getJSONObject(0).getString("url");
		return host + str;
	}
}
