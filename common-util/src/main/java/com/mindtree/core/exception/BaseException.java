package com.mindtree.core.exception;

import org.springframework.http.HttpStatus;

import com.google.common.base.Joiner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException{

	private static final long serialVersionUID = 3025482203572837499L;
	private String errorKey;
    private Throwable exception;
    private HttpStatus httpStatus;
    /**
     * A non null value of exceptionData can be used for the processing error key with meaningful data.
     */
    private Object exceptionData;

    public BaseException(String errorKey, HttpStatus httpStatus, String... message) {
        super(Joiner.on("").skipNulls().join(message));
        this.errorKey = errorKey;
        this.httpStatus = httpStatus;
    }

    public BaseException(String errorKey, Throwable ex, HttpStatus httpStatus, String... message) {
        super(Joiner.on("").skipNulls().join(message));
        this.errorKey = errorKey;
        this.exception = ex;
        this.httpStatus = httpStatus;
    }

    public BaseException(String errorKey, Object errorData, HttpStatus httpStatus, Object... message) {
        super(Joiner.on("").skipNulls().join(message));
        this.errorKey = errorKey;
        this.exceptionData = errorData;
        this.httpStatus = httpStatus;
    }
}
