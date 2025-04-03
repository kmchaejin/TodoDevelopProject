package com.example.tododevelopproject.controller;

import com.example.tododevelopproject.dto.ScheduleRequestDto;
import com.example.tododevelopproject.dto.ScheduleResponseDto;
import com.example.tododevelopproject.dto.ScheduleUpdateRequestDto;
import com.example.tododevelopproject.dto.ScheduleWithoutIdResponseDto;
import com.example.tododevelopproject.service.ScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
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
    public ResponseEntity<ScheduleWithoutIdResponseDto> findById(@NotNull @PathVariable Long id){
        return new ResponseEntity<>(scheduleService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){
        return new ResponseEntity<>(scheduleService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@NotNull @PathVariable Long id){
        scheduleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleWithoutIdResponseDto> update(@NotNull @PathVariable Long id, @RequestBody ScheduleUpdateRequestDto requestDto){
        return new ResponseEntity<>(scheduleService.update(id, requestDto), HttpStatus.OK);
    }
}
