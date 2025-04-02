package com.example.tododevelopproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String name;
    @NotBlank
    private String email;
    @NotNull
    private String password;
}
