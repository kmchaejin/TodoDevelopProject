package com.example.tododevelopproject.dto.user;

import com.example.tododevelopproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserWithoutIdResponseDto {
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserWithoutIdResponseDto(User foundUser) {
        this.name = foundUser.getName();
        this.email = foundUser.getEmail();
        this.createdAt = foundUser.getCreatedAt();
        this.updatedAt = foundUser.getUpdatedAt();
    }
}
