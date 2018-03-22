 package com.xbw.website;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xbw.website.utils.RedisService;



@RunWith(SpringRunner.class)
@SpringBootTest
 public class RedisTest {

     @Autowired
     RedisService redisService;

     @Before
     public void setUp() {

     }

     @Test
     public void get() {
         redisService.add("userName", "xbw", 10L);
         String userName = redisService.get("userName");
         System.out.println(userName);
     }
 }