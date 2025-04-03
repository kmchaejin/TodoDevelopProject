package com.example.tododevelopproject.service;

import com.example.tododevelopproject.dto.comment.CommentRequestDto;
import com.example.tododevelopproject.dto.comment.CommentResponseDto;
import com.example.tododevelopproject.entity.Comment;
import com.example.tododevelopproject.entity.Schedule;
import com.example.tododevelopproject.entity.User;
import com.example.tododevelopproject.repository.CommentRepository;
import com.example.tododevelopproject.repository.ScheduleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto save(@Valid CommentRequestDto requestDto, User user) {
        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId()).orElseThrow(NoSuchElementException::new);
        Comment comment = new Comment(schedule, user, requestDto.getContents());
        //commentRepository.save(requestDto.getScheduleId(), user.getId(), requestDto.getContents());
        //CommentResponseDto commentResponseDto = new CommentResponseDto(commentRepository.save(requestDto.getScheduleId(), user.getId(), requestDto.getContents()));
        return null;
    }
}
