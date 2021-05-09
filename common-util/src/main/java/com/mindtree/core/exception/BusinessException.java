package com.mindtree.core.exception;

import com.google.common.base.Joiner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorKey;
    private Throwable exception;
    /**
     * A non null value of exceptionData can be used for the processing error key with meaningful data.
     */
    private Object exceptionData;

    public BusinessException(String errorKey, String... message) {
        super(Joiner.on("").skipNulls().join(message));
        this.errorKey = errorKey;
    }

    public BusinessException(String errorKey, Throwable ex, String... message) {
        super(Joiner.on("").skipNulls().join(message));
        this.errorKey = errorKey;
        this.exception = ex;
    }

    public BusinessException(String errorKey, Object errorData, Object... message) {
        super(Joiner.on("").skipNulls().join(message));
        this.errorKey = errorKey;
        this.exceptionData = errorData;
    }

}
