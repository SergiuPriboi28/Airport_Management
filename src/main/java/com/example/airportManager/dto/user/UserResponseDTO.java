package com.example.airportManager.dto.user;

import com.example.airportManager.model.UserStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(
        String email,
        String phone,
        UserStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UUID employeeProfileId,
        UUID passengerProfileId
) {
}
