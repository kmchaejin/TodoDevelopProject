package com.example.tododevelopproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String title;
    private String contents;
    @NotNull(message = "ID는 필수 입력값입니다.")
    private Long userId;
}
