package com.example.tododevelopproject.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String title;
    private String contents;
    private Long userId;
}
