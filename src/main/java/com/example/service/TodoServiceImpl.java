package com.example.service;

import com.example.dto.TodoDTO;
import com.example.dto.TodoCreateDTO;
import com.example.entity.TodoEntity;
import com.example.entity.UserEntity;
import com.example.repository.TodoRepository;
import com.example.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoServiceImpl(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TodoDTO> getAllTodos() {
        UserEntity user = getCurrentUser();
        return todoRepository.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TodoDTO createTodo(TodoCreateDTO todoDTO) {
        UserEntity user = getCurrentUser();
        
        TodoEntity todo = new TodoEntity();
        todo.setTitle(todoDTO.getTitle());
        todo.setCreatedAt(LocalDateTime.now());
        todo.setUser(user);
        
        return convertToDTO(todoRepository.save(todo));
    }

    @Override
    public void deleteTodo(Long id) {
        UserEntity user = getCurrentUser();
        TodoEntity todo = todoRepository.findById(id).orElseThrow();
        if (todo.getUser().getId().equals(user.getId())) {
            todoRepository.delete(todo);
        }
    }

    private UserEntity getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow();
    }

    private TodoDTO convertToDTO(TodoEntity todo) {
        TodoDTO dto = new TodoDTO();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        dto.setCreatedAt(todo.getCreatedAt());
        return dto;
    }
}