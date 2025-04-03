package com.example.tododevelopproject.handler;

import com.example.tododevelopproject.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static com.example.tododevelopproject.exception.ErrorCode.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){

        ErrorResponseDto responseDto = new ErrorResponseDto(Timestamp.valueOf(LocalDateTime.now()), BAD_REQUEST.getStatus(), BAD_REQUEST.getError(), BAD_REQUEST.getCode(), e.getBindingResult().getFieldError().getDefaultMessage(), request.getRequestURI());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundKeyException(NoSuchElementException e, HttpServletRequest request){
        String msg;

        // email 입력 / id 입력 구분해서 에러메시지 출력
        if(e.getMessage() == null){
            msg = INCORRECT_KEY.getMessage();
        }else{
            msg = e.getMessage();
        }

        ErrorResponseDto responseDto = new ErrorResponseDto(Timestamp.valueOf(LocalDateTime.now()), INCORRECT_KEY.getStatus(), INCORRECT_KEY.getError(), INCORRECT_KEY.getCode(), msg, request.getRequestURI());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleWrongPasswordException(IllegalArgumentException e, HttpServletRequest request){

        ErrorResponseDto responseDto = new ErrorResponseDto(Timestamp.valueOf(LocalDateTime.now()), INCORRECT_PASSWORD.getStatus(), INCORRECT_PASSWORD.getError(), INCORRECT_PASSWORD.getCode(), INCORRECT_PASSWORD.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
    }
}
