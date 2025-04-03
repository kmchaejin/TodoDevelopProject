package com.example.tododevelopproject.dto.common;

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

    public ErrorResponseDto(Timestamp timestamp, int status, String error, String code, String message, String requestURI) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.code = code;
        this.message = message;
        this.path = requestURI;
    }
}
