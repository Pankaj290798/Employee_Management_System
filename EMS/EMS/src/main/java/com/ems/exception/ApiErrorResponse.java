package com.ems.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiErrorResponse {
    private  String errorCode;
    private  String message;
    private  Integer statusCode;
    private  String statusName;

    public ApiErrorResponse(String errorCode, Integer statusCode, String message, String statusName) {

    }

    public ApiErrorResponse(String errorCode, String message, int value, String name) {
        this.errorCode = errorCode;
        this.statusCode = value;
        this.message = message;
        this.statusName = name;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusName() {
        return statusName;
    }
}