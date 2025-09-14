package com.chch.skj.auth_server.auth;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

	@GetMapping(path = "/test")
	public ResponseEntity<String> Test(){
		
		var temp = new ResponseEntity<>("Test1123123", HttpStatus.OK);
		
		return temp;
	}
}
