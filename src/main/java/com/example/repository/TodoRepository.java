package com.example.repository;

import com.example.entity.TodoEntity;
import com.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> findByUserOrderByCreatedAtDesc(UserEntity user);
}