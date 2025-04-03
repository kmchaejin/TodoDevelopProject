package com.example.tododevelopproject.repository;

import com.example.tododevelopproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    
    default User findByEmailOrElseThrow(String email){
        // Todo: 로그인 실패 예외처리 enum으로 리팩토링
        return findUserByEmail(email).orElseThrow(() -> new NoSuchElementException("존재하지 않는 사용자입니다."));
    }
}
