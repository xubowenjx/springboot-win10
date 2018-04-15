package com.xbw.website.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @ClassName: I18nResolver
 * @Description: 国际化解析器
 * @author: xubowen
 * @date: 2018年3月27日 下午10:49:36
 *
 */
public class I18nResolver implements LocaleResolver {
	// 语言参数
	private static final String LANG = "lang";
	private static final String LANG_KEY = "i18n";

	/**
	 * @Title: resolveLocale
	 * @Description: 切换语言
	 * @param req
	 * @return
	 * @see org.springframework.web.servlet.LocaleResolver#resolveLocale(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Locale resolveLocale(HttpServletRequest req) {
		 
		Locale local = null;
		String lang = req.getParameter(LANG);
		if (!StringUtils.isEmpty(lang)) {
			String[] locale = lang.split("_");
			if (locale != null && locale.length == 2) {
				local = new Locale(locale[0], locale[1]);
			}

		}
		HttpSession session = req.getSession(true);
		if (local != null) {//有参数做
			// 放入session
			 
		} else {//没有就取
			Locale localSession = (Locale) session.getAttribute(LANG_KEY);
			if (localSession != null) {
				local = localSession;
			} else {//否则取默认
				local = Locale.getDefault();
			}
		}
		session.setAttribute(LANG_KEY, local);
		return local;
	}

	@Override
	public void setLocale(HttpServletRequest req, HttpServletResponse resp, Locale locale) {
		 
	}

}
