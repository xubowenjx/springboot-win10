package com.xbw.website;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xbw.website.bo.UserBo;
import com.xbw.website.business.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

	@Autowired
	private UserService userService;

	@Before
	public void setUp() {

	}

	@Test
	public void get() {
		UserBo bo = new UserBo();
		bo.setUserName("w33");
		bo.setUserPassword("123");
		bo.setUserType("1");
		//userService.save(bo);
		List<UserBo> users = userService.getUserList(bo);
		System.out.println(users);
	}
}