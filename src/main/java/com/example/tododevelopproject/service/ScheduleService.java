package com.example.tododevelopproject.service;

import com.example.tododevelopproject.dto.ScheduleRequestDto;
import com.example.tododevelopproject.dto.ScheduleResponseDto;
import com.example.tododevelopproject.dto.ScheduleWithoutIdResponseDto;
import com.example.tododevelopproject.entity.Schedule;
import com.example.tododevelopproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    //private final UserRepository userRepository;

    public ScheduleResponseDto save(ScheduleRequestDto requestDto) {

        // 자동 생성된 id값 get
        //User findUser = userRepository.findUserByUsernameOrElseThrow(requestDto.getName());

        Schedule schedule = new Schedule(requestDto.getTitle(), requestDto.getContents(), requestDto.getName());
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents(), savedSchedule.getName(), savedSchedule.getCreatedAt(), savedSchedule.getUpdatedAt());
    }

    public ScheduleWithoutIdResponseDto findById(Long id) {
        Optional<Schedule> opFoundSchedule = scheduleRepository.findById(id);
        Schedule foundSchedule = opFoundSchedule.orElseThrow(() -> new NoSuchElementException("존재하지 않는 id입니다."));

        return new ScheduleWithoutIdResponseDto(foundSchedule.getTitle(), foundSchedule.getContents(), foundSchedule.getName(), foundSchedule.getCreatedAt(), foundSchedule.getUpdatedAt());
    }

    public List<ScheduleResponseDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponseDto> responseDtoList = new ArrayList<>(); // 구현체 생성 : schedules가 null이어도 null이 아닌 빈 배열 add 가능

        for(Schedule schedule : schedules){
            responseDtoList.add(new ScheduleResponseDto(schedule));
        }

        return responseDtoList;
    }
}
