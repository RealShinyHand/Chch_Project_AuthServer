package com.chch.skj.auth_server.auth.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityInnerDto<T extends Object> {

	private T data = null;
	private List<String> details = null;
	
	public ResponseEntityInnerDto(T data, String... details) {
		
		this.data = data;
		this.details = new ArrayList<String>(details.length);
		
		for(int i = 0 ; i < details.length ; i++) {
			this.details.add(details[i]);
		}
	}
	
	public ResponseEntityInnerDto(T data, List<String> detailList) {
		
		this.data = data;
		this.details = new ArrayList<>();
		this.details.addAll(detailList);
		
	}
	
	public static <T> ResponseEntity<ResponseEntityInnerDto<T>> fromToResEntity(T data, HttpStatus httpStatus,String... details){
		
		var dataDto = new ResponseEntityInnerDto<T>(data,details);
		
		var res = new ResponseEntity<ResponseEntityInnerDto<T>>(dataDto,httpStatus);
		
		return res;
		
		
	}
	
	
}
