package com.example.tododevelopproject.service;

import com.example.tododevelopproject.dto.ScheduleRequestDto;
import com.example.tododevelopproject.dto.ScheduleResponseDto;
import com.example.tododevelopproject.entity.Schedule;
import com.example.tododevelopproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    //private final UserRepository userRepository;

    public ScheduleResponseDto save(ScheduleRequestDto requestDto) {

        // 자동 생성된 id값 get
        //User findUser = userRepository.findUserByUsernameOrElseThrow(requestDto.getName());

        Schedule schedule = new Schedule(requestDto.getTitle(), requestDto.getContents());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents(), requestDto.getName(), savedSchedule.getCreatedAt(), savedSchedule.getUpdatedAt());
    }
}
