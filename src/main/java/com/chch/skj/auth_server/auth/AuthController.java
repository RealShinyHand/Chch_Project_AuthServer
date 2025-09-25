package com.chch.skj.auth_server.auth;
import java.util.Map;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chch.skj.auth_server.auth.dto.JWTPair;
import com.chch.skj.auth_server.auth.exception.UserVerificationFailException;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {

	
	private final AuthService authService ;
	
    
	@GetMapping(path = "/test")
	public ResponseEntity<String> Test(){
		
		var temp = new ResponseEntity<>("Test1123123", HttpStatus.OK);
		
		return temp;
	}
	
	@PostMapping(path="/login")
	public ResponseEntity<?> Login(@RequestBody Map<String,String> map){
		
		
		String id = map.get("userId");
		String password = map.get("password");
		
		if(id == null || id.isBlank()) {
			return new ResponseEntity<>("ID 가 비어있습니다.", HttpStatus.BAD_REQUEST);
		}else if(password == null || password.isBlank()) {
			return	new ResponseEntity<>("Password가 비어있습니다.",HttpStatus.BAD_REQUEST);
		}
		
		JWTPair temp;
		try {
			temp = authService.login(id, password);
			return new ResponseEntity<JWTPair>(temp,HttpStatus.OK);
		} catch (UserVerificationFailException e) {
			return	new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		}	
		
		
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
