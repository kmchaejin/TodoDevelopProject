package com.example.tododevelopproject.handler;

import com.example.tododevelopproject.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static com.example.tododevelopproject.exception.ErrorCode.INCORRECT_EMAIL;
import static com.example.tododevelopproject.exception.ErrorCode.INCORRECT_PASSWORD;

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
    public ResponseEntity<ErrorResponseDto> handleNoSuchElementException(NoSuchElementException e, HttpServletRequest request){
        // findUserByEmail,findById - 존재하지 않는 값으로 데이터 조회 예외처리 (핸들러에서 메시지 설정하기)
        ErrorResponseDto responseDto = new ErrorResponseDto(Timestamp.valueOf(LocalDateTime.now()), INCORRECT_EMAIL, request.getRequestURI());

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request){
        ErrorResponseDto responseDto = new ErrorResponseDto(Timestamp.valueOf(LocalDateTime.now()), INCORRECT_PASSWORD, request.getRequestURI());

        return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
    }
}
