package com.example.tododevelopproject.controller;

import com.example.tododevelopproject.dto.schedule.ScheduleRequestDto;
import com.example.tododevelopproject.dto.schedule.ScheduleResponseDto;
import com.example.tododevelopproject.dto.schedule.ScheduleUpdateRequestDto;
import com.example.tododevelopproject.dto.schedule.ScheduleWithoutIdResponseDto;
import com.example.tododevelopproject.service.ScheduleService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ScheduleResponseDto> save(@Valid @RequestBody ScheduleRequestDto requestDto){
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        scheduleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleWithoutIdResponseDto> update(@PathVariable Long id, @RequestBody ScheduleUpdateRequestDto requestDto){
        return new ResponseEntity<>(scheduleService.update(id, requestDto), HttpStatus.OK);
    }
}
