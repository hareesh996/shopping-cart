package com.mindtree.web.exception;

import java.util.Locale;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mindtree.core.exception.BaseException;
import com.mindtree.core.exception.BusinessException;
import com.mindtree.web.dto.Response;

/**
 * @Desc
 *       <p>
 *       A Exception handler for the Rest services, that resolves the
 *       following exception
 *       <ul>
 *       <li>BaseException (BusinessException)</li>
 *       <li>Various Spring Exceptions,</li>
 *       <li>Unhandled exceptions</li>
 *       <ul>
 *       </p>
 * @author HChandra
 * @version 1.0
 */
@ControllerAdvice
@RestController
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ResponseEntity<Object> responseEntity = null;
		Locale locale = request.getLocale();
		if (ex instanceof BaseException) {
			responseEntity = handleApplicationException((BaseException) ex, body, locale);
		}else {
			responseEntity = handleGenericException(ex, locale);
		}
		return responseEntity;
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(value = { BaseException.class, BusinessException.class })
	protected ResponseEntity<Object> handleConflict(BaseException ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		return handleExceptionInternal(ex, null, headers, HttpStatus.ACCEPTED, request);
	}

	/**
	 * @Desc
	 *       <p>
	 *       As the exception is an unknown exception, we will send the generic
	 *       message as a response.
	 *       </p>
	 * @Author HChandra
	 */
	private ResponseEntity<Object> handleGenericException(Exception ex, Locale requestlocale) {
		return new ResponseEntity<>(Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR, "common.unknow.error").build(null), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<Object> handleApplicationException(BaseException ex, Object body, Locale requestlocale) {
		return new ResponseEntity<>(Response.builder().status(ex.getHttpStatus(), ex.getErrorKey()).build(null), ex.getHttpStatus());
	}

}

