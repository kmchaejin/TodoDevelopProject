package com.example.tododevelopproject.dto;

import com.example.tododevelopproject.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ErrorResponseDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp timestamp;
    private int status;
    private String error;
    private String code;
    private String message;
    private String path;
    //private List<FieldError> fieldErrors;

    public ErrorResponseDto(Timestamp timestamp, ErrorCode errorCode, String requestURI) {
        this.timestamp = timestamp;
        this.status = errorCode.getStatus();
        this.error = errorCode.getError();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.path = requestURI;
    }
}
