package com.example.tododevelopproject.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ScheduleWithoutIdResponseDto {
    private String title;
    private String contents;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
