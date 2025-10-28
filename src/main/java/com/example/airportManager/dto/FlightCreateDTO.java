package com.example.airportManager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record FlightCreateDTO(
        @NotBlank String code,
        @NotBlank String gate,
        @NotNull LocalDateTime departureScheduled,
        @NotNull LocalDateTime arrivalScheduled,
        @NotNull String aircraftId,
        @NotNull Long routeId
) {
}
