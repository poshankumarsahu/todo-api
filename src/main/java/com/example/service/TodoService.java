package com.example.service;

import com.example.dto.TodoDTO;
import com.example.dto.TodoCreateDTO;
import java.util.List;

public interface TodoService {
    List<TodoDTO> getAllTodos();
    TodoDTO createTodo(TodoCreateDTO todoDTO);
    void deleteTodo(Long id);
}