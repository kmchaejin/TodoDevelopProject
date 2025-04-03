package com.example.tododevelopproject.controller;

import com.example.tododevelopproject.dto.comment.CommentRequestDto;
import com.example.tododevelopproject.dto.comment.CommentResponseDto;
import com.example.tododevelopproject.entity.User;
import com.example.tododevelopproject.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.tododevelopproject.config.Const.LOGIN_USER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> save(@Valid @RequestBody CommentRequestDto requestDto, HttpServletRequest httpRequest){
        HttpSession httpSession = httpRequest.getSession(false);
        User loginUser = (User) httpSession.getAttribute(LOGIN_USER);

        return new ResponseEntity<>(commentService.save(requestDto, loginUser), HttpStatus.CREATED);
    }
}
