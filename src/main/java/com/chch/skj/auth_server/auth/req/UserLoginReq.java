package com.chch.skj.auth_server.auth.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginReq {
    private String userID;
    private String passWord;
}
