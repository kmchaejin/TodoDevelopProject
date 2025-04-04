package com.example.tododevelopproject.controller;

import com.example.tododevelopproject.dto.common.LoginRequestDto;
import com.example.tododevelopproject.dto.common.LoginResponseDto;
import com.example.tododevelopproject.dto.user.UserRequestDto;
import com.example.tododevelopproject.dto.user.UserResponseDto;
import com.example.tododevelopproject.dto.user.UserWithoutIdResponseDto;
import com.example.tododevelopproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.tododevelopproject.config.Const.LOGIN_USER;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    /*
    로그인 API
    세션을 할당하여 전체 API 요청 시 필터에서 예외가 발생하지 않도록 하는 기능
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request){
        LoginResponseDto responseDto = userService.login(requestDto);

        HttpSession session = request.getSession();

        // Todo: 리팩토링 필요해보임
        UserResponseDto loginUser = new UserResponseDto(responseDto.getId(), userService.findById(responseDto.getId()));
        session.setAttribute(LOGIN_USER, loginUser);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /*
    로그아웃 API
    세션을 삭제하여 이후 전체 API 접근 시 필터에서 예외 발생
     */
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
    public ResponseEntity<UserWithoutIdResponseDto> findById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserWithoutIdResponseDto> update(@PathVariable Long id, @Valid @RequestBody UserRequestDto requestDto){
        return new ResponseEntity<>(userService.update(id, requestDto), HttpStatus.OK);
    }
}
