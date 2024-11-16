package com.example.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TodoDTO {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
}