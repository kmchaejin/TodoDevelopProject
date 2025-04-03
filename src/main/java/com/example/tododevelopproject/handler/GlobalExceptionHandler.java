package com.example.tododevelopproject.handler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Todo: 예외 응답 메시지 형식 만들기(과제 발제 예시 참고)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        // @Valid 예외처리
        // @Pattern 예외처리
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException(ConstraintViolationException e){
        // @Validated - id값 notnull 유효성 검사 예외처리 (핸들러에서 메시지 설정하기)
    }

    @ExceptionHandler(NoSuchElementException.class)
    public void handleNoSuchElementException(NoSuchElementException e){
        // findUserByEmail,findById - 존재하지 않는 값으로 데이터 조회 예외처리 (핸들러에서 메시지 설정하기)

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(IllegalArgumentException e){
        // 로그인 시 비밀번호 불일치 예외처리
    }
}
