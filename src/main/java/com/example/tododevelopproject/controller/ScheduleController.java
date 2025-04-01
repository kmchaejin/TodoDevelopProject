package com.example.tododevelopproject.controller;

import com.example.tododevelopproject.dto.ScheduleRequestDto;
import com.example.tododevelopproject.dto.ScheduleResponseDto;
import com.example.tododevelopproject.dto.ScheduleWithoutIdResponseDto;
import com.example.tododevelopproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto requestDto){
        return new ResponseEntity<>(scheduleService.save(requestDto),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleWithoutIdResponseDto> findById(@PathVariable Long id){
        return new ResponseEntity<>(scheduleService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){
        return new ResponseEntity<>(scheduleService.findAll(), HttpStatus.OK);
    }
}
