package com.mindtree.web.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class Response<T>{
	
	private T body;
	private HttpStatus status;
	private List<String> errorKeys;
	
	public static <T> ResponseBuilder<T> builder() {
		return new ResponseBuilder<T>();
	}
	
	public static class ResponseBuilder<T>{
		
		private Response<T> response;
		
		ResponseBuilder() {
			this.response = new Response<T>();
		}
		
		public ResponseEntity<Response<T>> ok(T body){
			this.response.status = HttpStatus.OK;
			return this.build(body);
		}
		
		public ResponseBuilder<T> status(HttpStatus status, String ...errorKeys){
			this.response.status = status;
			this.response.errorKeys = Arrays.asList(errorKeys);
			return this;
		}
		
		public ResponseEntity<Response<T>> build(T body){
			this.response.body = body;
			return ResponseEntity.status(this.response.status).body(this.response);
		}
		
	}
	
}
