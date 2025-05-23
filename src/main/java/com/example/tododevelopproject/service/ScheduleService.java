package com.example.tododevelopproject.service;

import com.example.tododevelopproject.dto.schedule.ScheduleRequestDto;
import com.example.tododevelopproject.dto.schedule.ScheduleResponseDto;
import com.example.tododevelopproject.dto.schedule.ScheduleUpdateRequestDto;
import com.example.tododevelopproject.dto.schedule.ScheduleWithoutIdResponseDto;
import com.example.tododevelopproject.entity.Schedule;
import com.example.tododevelopproject.entity.User;
import com.example.tododevelopproject.repository.ScheduleRepository;
import com.example.tododevelopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleResponseDto save(ScheduleRequestDto requestDto) {

        // 자동 생성된 id값 get
        User foundUser = userRepository.findById(requestDto.getUserId()).orElseThrow(NoSuchElementException::new);

        Schedule schedule = new Schedule(requestDto.getTitle(), requestDto.getContents(), foundUser);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents(), foundUser.getId(), savedSchedule.getCreatedAt(), savedSchedule.getUpdatedAt());
    }

    public ScheduleWithoutIdResponseDto findById(Long id) {
        Schedule foundSchedule = scheduleRepository.findById(id).orElseThrow(NoSuchElementException::new);

        return new ScheduleWithoutIdResponseDto(foundSchedule.getTitle(), foundSchedule.getContents(), foundSchedule.getUser().getId(), foundSchedule.getCreatedAt(), foundSchedule.getUpdatedAt());
    }

    public List<ScheduleResponseDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponseDto> responseDtoList = new ArrayList<>();

        for(Schedule schedule : schedules){
            responseDtoList.add(new ScheduleResponseDto(schedule));
        }

        return responseDtoList;
    }

    public void deleteById(Long id) {
        scheduleRepository.findById(id).orElseThrow(NoSuchElementException::new);
        scheduleRepository.deleteById(id);
    }

    @Transactional
    public ScheduleWithoutIdResponseDto update(Long id, ScheduleUpdateRequestDto requestDto) {
        Schedule foundSchedule = scheduleRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if(requestDto.getTitle() != null && !requestDto.getTitle().isEmpty()){
            foundSchedule.setTitle(requestDto.getTitle());
        }
        if(requestDto.getContents() != null && !requestDto.getContents().isEmpty()){
            foundSchedule.setContents(requestDto.getContents());
        }

        return new ScheduleWithoutIdResponseDto(foundSchedule.getTitle(), foundSchedule.getContents(), foundSchedule.getId(), foundSchedule.getCreatedAt(), foundSchedule.getUpdatedAt());
    }
}
