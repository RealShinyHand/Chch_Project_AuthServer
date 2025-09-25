package com.chch.skj.auth_server.auth;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthDao {

	void selectUser(String userId, String password);

}
