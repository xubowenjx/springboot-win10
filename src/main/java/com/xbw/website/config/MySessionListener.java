 package com.xbw.website.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
@WebListener
public class MySessionListener implements  HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session=se.getSession();  
		System.out.println("session  创建");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session=se.getSession();  
		System.out.println("session  销毁");
	}

}
