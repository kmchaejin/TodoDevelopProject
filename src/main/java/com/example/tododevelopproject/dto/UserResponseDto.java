package com.example.tododevelopproject.dto;

import com.example.tododevelopproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }

    // 로그인 api에 값을 저장할 다른 방법은 없나...?
    public UserResponseDto(Long id, UserWithoutIdResponseDto dto) {
        this.id = id;
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.createdAt = dto.getCreatedAt();
        this.updatedAt = dto.getUpdatedAt();
    }
}
