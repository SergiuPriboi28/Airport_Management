package com.example.airportManager.dto.user;

import com.example.airportManager.model.UserStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserCreateDTO(
        String email,
        String phone,
        String passwordHash,
        UserStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
