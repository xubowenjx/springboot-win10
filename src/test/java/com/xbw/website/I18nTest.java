package com.xbw.website;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class I18nTest {

	@Autowired
	private MessageSource messageSource;

	@Before
	public void setUp() {

	}

	@Test
	public void get() {
		Locale zhCn = new Locale("zh", "CN");
		Locale enUs = new Locale("en", "US");
		String strZh = messageSource.getMessage("public.btn.query", null, zhCn);
		String strEn = messageSource.getMessage("public.btn.query", null, enUs);
		String strZhHellow = messageSource.getMessage("message.hello", new Object[] { "徐博文" }, zhCn);
		String strEnHellow = messageSource.getMessage("message.hello", new Object[] { "xubowen" }, enUs);
		System.out.println("strZh:" + strZh);
		System.out.println("strEn:" + strEn);
		System.out.println("strZhHellow:" + strZhHellow);
		System.out.println("strEnHellow:" + strEnHellow);
	}
}