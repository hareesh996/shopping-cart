package com.mindtree.core.exception;

import org.springframework.http.HttpStatus;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class BusinessException extends  BaseException{

	private static final long serialVersionUID = 1L;

    public BusinessException(String errorKey, HttpStatus httpStatus, String... message) {
    	super(errorKey, httpStatus, message);
    }

    public BusinessException(String errorKey, Throwable ex, HttpStatus httpStatus, String... message) {
    	super(errorKey, ex,httpStatus, message);
    }

    public BusinessException(String errorKey, Object errorData, HttpStatus httpStatus, Object... message) {
        super(errorKey, errorData, httpStatus,  message);
    }

}
