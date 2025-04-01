package com.example.tododevelopproject.controller;

import com.example.tododevelopproject.dto.ScheduleRequestDto;
import com.example.tododevelopproject.dto.ScheduleResponseDto;
import com.example.tododevelopproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto requestDto){
        return new ResponseEntity<>(scheduleService.save(requestDto),HttpStatus.CREATED);
    }
}
