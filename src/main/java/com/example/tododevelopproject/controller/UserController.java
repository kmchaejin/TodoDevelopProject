package com.example.tododevelopproject.controller;

import com.example.tododevelopproject.dto.*;
import com.example.tododevelopproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public static final String LOGIN_USER = "loginUser";

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request){
        LoginResponseDto responseDto = userService.login(requestDto);

        HttpSession session = request.getSession(); // 세션 할당

        // 다른 방법은 없나...?
        UserResponseDto loginUser = new UserResponseDto(responseDto.getId(), userService.findById(responseDto.getId()));
        session.setAttribute(LOGIN_USER, loginUser);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 로그아웃 API
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if(session != null){
            session.invalidate();
        }

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
