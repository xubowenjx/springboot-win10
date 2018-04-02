package com.xbw.website.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xbw.website.bo.UserBo;

@Mapper
public interface UserMapper {
	public List<UserBo> getUserList(UserBo bo);

	public int save(UserBo bo);
}
