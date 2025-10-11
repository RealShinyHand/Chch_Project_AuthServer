package com.chch.skj.auth_server.auth.res;

import com.chch.skj.auth_server.auth.dto.Role;

public record UserRes(String userName, String userUID, Role role) {
	
}
