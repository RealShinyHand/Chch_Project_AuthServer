package com.chch.skj.auth_server.auth.dto;

public record JWTPair(String authToken,String refreshToken,boolean isValid) {

}
