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
import com.chch.skj.auth_server.auth.dto.UserDO;
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
	public ResponseEntity<?> Login(@RequestBody Map<String,String> map,HttpServletResponse response){
		
		
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
			
			var cookie =  new Cookie("authToken",String.format("%s", temp.authToken()));
			cookie.setPath("/");
			cookie.setMaxAge(60*30);//30분
			cookie.setSecure(true);
			
			response.addCookie(cookie);
			
			
			cookie =  new Cookie("refreshToken",String.format("%s", temp.refreshToken()));
			cookie.setMaxAge(60*60*24);//하루
			cookie.setSecure(true);
			
			response.addCookie(cookie);
			
			
			return new ResponseEntity<JWTPair>(temp,HttpStatus.OK);
		} catch (UserVerificationFailException e) {
			return	new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		}	
		
		
	}
	
	@GetMapping(path = "/valid")
	public ResponseEntity<?> Valid(HttpServletRequest request, HttpServletResponse response) throws UserVerificationFailException{
		
		
		String authToken = "";
		String refreshToken = "";
		
		
		for(var cookie : request.getCookies()) {
			String cookieName = cookie.getName();
			if(cookieName==null || cookieName == "") {
				cookieName = cookie.getName();
			}
			
			if(cookieName == null || cookieName == "") {
				
				
			}else if("authToken".equals(cookieName) && "".equals(authToken)){
				
				authToken = cookie.getValue();
				
			}else if("refreshToken".equals(cookieName)) {
				refreshToken = cookie.getValue();
			}
		}
		
		
		boolean isAuthenticated = false;
		UserDO userDo  = null;		
		if(authToken != "") {
			int splitIndex = authToken.indexOf(" ");
			if(splitIndex != -1) {
				String tokenUsage = authToken.substring(0,splitIndex);
				String token = authToken.substring(splitIndex,authToken.length());
				
			    userDo = authService.isValid(authToken);	
			    
				if(userDo == null) {
					isAuthenticated = false;
				}else {
					isAuthenticated = true;
				}
			}
		}

		//나중에 구현
		if(!isAuthenticated) {
			if(refreshToken != "") {
				userDo = authService.isValid(refreshToken);	
			
				if(userDo != null) {
					
		//			authService.login(userDo.userName(), userDo.)
				}
			}
				
		}
		
		{//여기는 그냥 성공한거
			
			
		}
		
		
		return null;
	}
}
