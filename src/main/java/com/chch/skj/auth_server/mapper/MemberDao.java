package com.chch.skj.auth_server.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chch.skj.auth_server.auth.dto.UserDto;

@Mapper
public interface MemberDao {
	
	
	public UserDto selectUserUserIdAndPassword(String userId,String passWord);
}
