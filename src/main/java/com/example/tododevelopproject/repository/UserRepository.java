package com.example.tododevelopproject.repository;

import com.example.tododevelopproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    
    default User findByEmailOrElseThrow(String email){
        return findUserByEmail(email).orElseThrow(() -> new NoSuchElementException("존재하지 않는 사용자입니다."));
    }
}
