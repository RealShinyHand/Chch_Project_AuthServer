package com.chch.skj.auth_server.auth.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private String userId;
	private String userPasswrod;
	private  Role role;
	private LocalDateTime createTime;
	private LocalDateTime expireTime;
	private boolean isDelete;
}
