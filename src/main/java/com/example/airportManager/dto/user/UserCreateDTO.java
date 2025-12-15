package com.example.airportManager.dto.user;

public record UserCreateDTO(
        String email,
        String phone,
        String password
) {
}
