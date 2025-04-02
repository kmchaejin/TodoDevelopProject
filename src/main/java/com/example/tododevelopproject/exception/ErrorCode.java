package com.example.tododevelopproject.exception;

public enum ErrorCode {
    NOT_FOUND_USER(401, "Unauthorized", "C001", "잘못된 입력값입니다");

    private final int status;
    private final String error;
    private final String code;
    private final String message;

    ErrorCode(int status, String error, String code, String message) {
        this.status = status;
        this.error = error;
        this.code = code;
        this.message = message;
    }
}
