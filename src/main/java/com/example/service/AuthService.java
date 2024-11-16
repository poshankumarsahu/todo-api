package com.example.service;

import com.example.dto.AuthRequestDTO;
import com.example.dto.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO register(AuthRequestDTO request);
    AuthResponseDTO authenticate(AuthRequestDTO request);
}