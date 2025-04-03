package com.example.tododevelopproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "유효한 이메일 주소를 입력하세요.")
    private String email;
    @NotNull(message = "비밀번호는 필수 입력값입니다.")
    private String password;
}
