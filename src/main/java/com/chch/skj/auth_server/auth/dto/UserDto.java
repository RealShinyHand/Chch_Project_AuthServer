package com.chch.skj.auth_server.auth.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/*
*
create table `cch_db`.t_user(

	uuid CHAR(36) DEFAULT (UUID()) PRIMARY KEY,
    userID CHAR(50),
    userPassword CHAR(100),
    `role` CHAR(10) default "BASIC", -- BASIC과 MANAGER존재
    createTime dateTime default now(),
    expireTime dateTime null,
    isDelete tinyint default 0,

      -- 유니크 인덱스
    UNIQUE INDEX idx_userId (userId)
);
* */


@Getter
@Setter
public class UserDto {
	private String userId;
	private String userPasswrod;
	private Role role;
	private LocalDateTime createTime;
	private LocalDateTime expireTime;
	private boolean isDelete;
}
