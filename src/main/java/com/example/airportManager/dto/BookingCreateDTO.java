package com.example.airportManager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookingCreateDTO(
        @NotBlank String pnr,
        @NotNull Long flightId,
        @NotNull Long passengerId
) {
}
