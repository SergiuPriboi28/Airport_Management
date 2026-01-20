package com.example.airportManager.dto.generals;

import com.example.airportManager.model.RoleName;

import java.util.Set;
import java.util.UUID;

public record AutoResponseDTO(
        UUID userId,
        String email,
        Set<RoleName> roles,
        String token
) {
}
