package com.example.tododevelopproject.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNAUTHORIZED(401, "Unauthorized", "C001", "로그인이 필요합니다."),
    INCORRECT_KEY(404, "Not Found", "C002", "존재하지 않는 ID입니다."),
    INCORRECT_PASSWORD(400, "Bad Request", "C003", "비밀번호가 일치하지 않습니다."),
    BAD_REQUEST(400, "Bad Request", "C004", "형식에 맞는 값을 입력해주세요.");

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
