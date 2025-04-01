package com.example.tododevelopproject.repository;

import com.example.tododevelopproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
