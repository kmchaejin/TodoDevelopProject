package com.example.tododevelopproject.controller;

import com.example.tododevelopproject.dto.*;
import com.example.tododevelopproject.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    // 의존성 주입이 아니라 new로 인스턴스를 생성할 경우, 해당 객체를 사용하지 않는 상황에서도 인스턴스를 생성하게 됨 -> 리소스 낭비
    // 의존성을 주입하면 리소스 낭비를 막으면서도, 구현 클래스에 의존하지 않도록 해줌
    private final UserService userService;

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletResponse response){
        LoginResponseDto responseDto = userService.login(requestDto);

        // 로그인 성공 처리(쿠키 생성)
        Cookie cookie = new Cookie("userId", String.valueOf(responseDto.getId()));
        response.addCookie(cookie);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 로그아웃 API
    // HttpServletResponse은 스프링이 자동으로 주입해주기 때문에 매개변수로 전달 받음
    // response는 return할 필요없이 스프링이 자동으로 클라이언트에게 전달
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response){
        // 로그아웃 처리(쿠키 삭제)
        // 쿠키가 만료되지 않고 유지될 가능성을 배제하기 위해 userId를 null로 설정 -> 이중체크
        Cookie cookie = new Cookie("userId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody UserRequestDto requestDto){
        return new ResponseEntity<>(userService.save(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserWithoutIdResponseDto> findById(@NotNull @PathVariable Long id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@NotNull @PathVariable Long id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserWithoutIdResponseDto> update(@PathVariable Long id, @Valid @RequestBody UserRequestDto requestDto){
        return new ResponseEntity<>(userService.update(id, requestDto), HttpStatus.OK);
    }
}
