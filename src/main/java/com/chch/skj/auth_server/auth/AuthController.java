package com.chch.skj.auth_server.auth;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {

	
	private final AuthService authService = null;
	
	@GetMapping(path = "/test")
	public ResponseEntity<String> Test(){
		
		var temp = new ResponseEntity<>("Test1123123", HttpStatus.OK);
		
		return temp;
	}
	
	
	@GetMapping(path = "/valid")
	public ResponseEntity<?> Valid(HttpServletRequest request, HttpServletResponse response){
		
		request.getHeader(null);
		
		for(var cookie : request.getCookies()) {
			String k = cookie.getAttribute("authToken");
		}
		
		authService.isValid(null);
		
		
		var cookie =  new Cookie("authToken","");
		cookie.setSecure(true);
		
		response.addCookie(cookie);
		
		
		cookie =  new Cookie("refreshToken","");
		cookie.setSecure(true);
		
		response.addCookie(cookie);
		
		return null;
	}
}
