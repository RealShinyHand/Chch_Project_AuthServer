package com.chch.skj.auth_server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chch.skj.auth_server.auth.dto.UserDto;

@Mapper
public interface MemberDao {
	
	
	public UserDto selectUserUserIdAndPassword(@Param("userId") String userId, @ Param("passWord") String passWord);
}
