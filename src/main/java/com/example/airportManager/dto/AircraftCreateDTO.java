package com.example.airportManager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AircraftCreateDTO(
        @NotBlank String tailNumber,
        @NotBlank String model,
        @NotBlank String seatMapRef,
        @NotBlank String status,
        @NotNull  int capacity
        ) {
}
