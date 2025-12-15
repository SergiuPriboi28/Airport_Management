package com.example.airportManager.dto.aircraft;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AircraftCreateDTO(
        String tailNumber,
        String model,
        String seatMapRef,
        String status,
        int capacity
        ) {
}
