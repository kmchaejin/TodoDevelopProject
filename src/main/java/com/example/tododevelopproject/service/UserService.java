package com.example.tododevelopproject.service;

import com.example.tododevelopproject.dto.UserRequestDto;
import com.example.tododevelopproject.dto.UserResponseDto;
import com.example.tododevelopproject.dto.UserWithoutIdResponseDto;
import com.example.tododevelopproject.entity.User;
import com.example.tododevelopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto save(UserRequestDto requestDto) {
        User user = new User(requestDto.getName(), requestDto.getEmail());
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getCreatedAt(), savedUser.getUpdatedAt());
    }

    public UserWithoutIdResponseDto findById(Long id) {
        User foundUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 id입니다."));
        return new UserWithoutIdResponseDto(foundUser.getName(), foundUser.getEmail(), foundUser.getCreatedAt(), foundUser.getUpdatedAt());
    }
}
