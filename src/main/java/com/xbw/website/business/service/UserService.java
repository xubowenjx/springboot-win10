 package com.xbw.website.business.service;

import java.util.List;

import com.xbw.website.bo.UserBo;

public interface UserService {
	 public List<UserBo> getUserList(UserBo bo);
	 public int save(UserBo bo);
}
