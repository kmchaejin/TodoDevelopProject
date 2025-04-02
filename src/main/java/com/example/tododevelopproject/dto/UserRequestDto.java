package com.example.tododevelopproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
