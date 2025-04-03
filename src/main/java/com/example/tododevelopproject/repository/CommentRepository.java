package com.example.tododevelopproject.repository;

import com.example.tododevelopproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Map<Long, Long>> {
}
