package com.example.tododevelopproject.dto.comment;

import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long scheduleId;
    private Long userId;
    private String contents;
}
