package com.example.tododevelopproject.service;

import com.example.tododevelopproject.dto.*;
import com.example.tododevelopproject.entity.User;
import com.example.tododevelopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto save(UserRequestDto requestDto) {
        User user = new User(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getCreatedAt(), savedUser.getUpdatedAt());
    }

    public UserWithoutIdResponseDto findById(Long id) {
        User foundUser = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return new UserWithoutIdResponseDto(foundUser.getName(), foundUser.getEmail(), foundUser.getCreatedAt(), foundUser.getUpdatedAt());
    }

    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> responseDtoList = new ArrayList<>();

        for(User user : users){
            responseDtoList.add(new UserResponseDto(user));
        }

        return responseDtoList;
    }

    public void deleteById(Long id) {
        userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        userRepository.deleteById(id);
    }

    public UserWithoutIdResponseDto update(Long id, UserRequestDto requestDto) {
        User foundUser = userRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if(requestDto.getName() != null && !requestDto.getName().isEmpty()){
            foundUser.setName(requestDto.getName());
        }

        if(requestDto.getEmail() != null && !requestDto.getEmail().isEmpty()){
            foundUser.setEmail(requestDto.getEmail());
        }

        userRepository.save(foundUser);

        return new UserWithoutIdResponseDto(foundUser);
    }

    public LoginResponseDto login(LoginRequestDto requestDto) {
        User foundUser = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new NoSuchElementException("가입되지 않은 이메일입니다."));

        if(!requestDto.getPassword().equals(foundUser.getPassword())){
            throw new IllegalArgumentException();
        }

        String message = foundUser.getName() + "님, 환영합니다";

        return new LoginResponseDto(foundUser.getId(), message);
    }
}
