package com.example.airportManager.dto.aircraft;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AircraftCreateDTO(
        @NotBlank(message = "Tail number is required.") String tailNumber,
        @NotBlank(message = "Model is required.") String model,
        @NotBlank(message = "Seat map reference is required.") String seatMapRef,
        @NotBlank(message = "Status is required.") String status,
        @NotNull(message = "Capacity must be provided.")
        @Min(value = 0, message = "Capacity has to be a positive number") // We can have ferry flights where only the crew is present
        @Max(value = 860, message = "Capacity cannot exceed 860") // Maximum of Airbus A380 is 853 passengers
        int capacity
        ) {
}
