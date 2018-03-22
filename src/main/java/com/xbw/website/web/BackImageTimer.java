package com.xbw.website.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

/**
 * @author xubowen
 * @date 2018/03/22 下载背景图片
 */
@Component
public class BackImageTimer {
	private Logger log = LoggerFactory.getLogger(WebController.class);
	@Autowired
	private RestTemplate restTemplate;
	//每天4：30：01执行任务
	@Scheduled(cron = "1 30 04 * * ?")
	public void init() {
		String url = "http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&nc=1521726697743&pid=hp&video=1&quiz=1&fav=1";
		JSONObject json = restTemplate.getForEntity(url, JSONObject.class).getBody();
		String str = json.getJSONArray("images").getJSONObject(0).getString("url");
		log.info(str);
	}
}
