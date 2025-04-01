package com.example.tododevelopproject.controller;

import com.example.tododevelopproject.dto.UserRequestDto;
import com.example.tododevelopproject.dto.UserResponseDto;
import com.example.tododevelopproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    // 의존성 주입이 아니라 new로 인스턴스를 생성할 경우, 해당 객체를 사용하지 않는 상황에서도 인스턴스를 생성하게 됨 -> 리소스 낭비
    // 의존성을 주입하면 리소스 낭비를 막으면서도, 구현 클래스에 의존하지 않도록 해줌
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto requestDto){
        return new ResponseEntity<>(userService.save(requestDto), HttpStatus.CREATED);
    }
}
