package com.example.tododevelopproject.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @NotNull(message = "ID는 필수 입력값입니다.")
    private Long scheduleId;
    @NotBlank(message = "댓글 내용은 필수 입력값입니다.")
    @Size(max = 500, message = "댓글 내용은 최대 500자까지 입력할 수 있습니다.")
    private String contents;
}
