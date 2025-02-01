package com.ems.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {
    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus;



    public ApplicationException(String message, String errorCode, String message1, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.message = message1;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
