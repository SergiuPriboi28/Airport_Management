package com.example.airportManager.dto.generals;

import com.example.airportManager.model.RoleName;

import java.util.UUID;

public record AutoResponseDTO(
        UUID userId,
        String email,
        RoleName role,
        String token
) {
}
