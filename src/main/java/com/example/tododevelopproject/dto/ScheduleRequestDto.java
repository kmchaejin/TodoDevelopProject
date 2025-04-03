package com.example.tododevelopproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    @NotBlank(message = "할일 제목은 필수 입력값입니다.")
    @Size(min = 2, max = 50, message = "할일 제목은 2~50자 이내여야 합니다.")
    private String title;
    @Size(max = 500, message = "할일 내용은 최대 500자까지 입력할 수 있습니다.")
    private String contents;
    @NotNull(message = "ID는 필수 입력값입니다.")
    private Long userId;
}
