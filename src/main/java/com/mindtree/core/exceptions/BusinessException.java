package com.mindtree.core.exceptions;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String errorKey;
	private Throwable exception;
	/**
	 * A non null value of exceptionData can be used for the processing error key with meaningful data.
	 */
	private Object exceptionData;
	
	public BusinessException(String errorKey, String ...message) {
		super(StringUtils.join(message));
		this.errorKey = errorKey;
	}
	
	public BusinessException(String errorKey, Throwable ex, String ...message) {
		super(StringUtils.join(message));
		this.errorKey = errorKey;
		this.exception = ex ;
	}
	
	public BusinessException(String errorKey, Object errorData, Object ...message) {
		super(StringUtils.join(message));
		this.errorKey = errorKey;
		this.exceptionData = errorData;
	}

}
