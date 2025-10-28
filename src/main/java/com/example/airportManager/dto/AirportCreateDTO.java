package com.example.airportManager.dto;

import jakarta.validation.constraints.NotBlank;

public record AirportCreateDTO(
        @NotBlank String iata,
        @NotBlank String icao,
        @NotBlank String name,
        @NotBlank String city,
        @NotBlank String country,
        @NotBlank String timezone
) {
}
