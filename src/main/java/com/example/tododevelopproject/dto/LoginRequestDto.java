package com.example.tododevelopproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @NotBlank //공백 불가
    private String email;
    @NotNull
    private String password;
}
