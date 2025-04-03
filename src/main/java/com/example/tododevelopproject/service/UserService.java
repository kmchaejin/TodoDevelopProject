package com.example.tododevelopproject.service;

import com.example.tododevelopproject.config.PasswordEncoder;
import com.example.tododevelopproject.dto.common.LoginRequestDto;
import com.example.tododevelopproject.dto.common.LoginResponseDto;
import com.example.tododevelopproject.dto.user.UserRequestDto;
import com.example.tododevelopproject.dto.user.UserResponseDto;
import com.example.tododevelopproject.dto.user.UserWithoutIdResponseDto;
import com.example.tododevelopproject.entity.User;
import com.example.tododevelopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto save(UserRequestDto requestDto) {
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(requestDto.getName(), requestDto.getEmail(), encodedPassword);
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

    @Transactional
    public UserWithoutIdResponseDto update(Long id, UserRequestDto requestDto) {
        User foundUser = userRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if(requestDto.getName() != null && !requestDto.getName().isEmpty()){
            foundUser.setName(requestDto.getName());
        }

        if(requestDto.getEmail() != null && !requestDto.getEmail().isEmpty()){
            foundUser.setEmail(requestDto.getEmail());
        }

        return new UserWithoutIdResponseDto(foundUser);
    }

    public LoginResponseDto login(LoginRequestDto requestDto) {
        User foundUser = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new NoSuchElementException("가입되지 않은 이메일입니다."));

        if(!passwordEncoder.matches(requestDto.getPassword(), foundUser.getPassword())){
            throw new IllegalArgumentException();
        }

        String message = foundUser.getName() + "님, 환영합니다";

        return new LoginResponseDto(foundUser.getId(), message);
    }
}
