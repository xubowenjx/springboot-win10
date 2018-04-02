package com.xbw.website.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbw.website.bo.UserBo;
import com.xbw.website.business.dao.UserMapper;
import com.xbw.website.business.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserBo> getUserList(UserBo bo) {
		return userMapper.getUserList(bo);
	}

	@Override
	public int save(UserBo bo) {
		 
		 return userMapper.save(bo);
	}

}
