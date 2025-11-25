package com.example.airportManager.dto.flight;

import com.example.airportManager.validation.ArrivalAfterDeparture;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@ArrivalAfterDeparture
public record FlightCreateDTO(
        @NotBlank(message = "Flight code is required.")
        @Size(min = 4, max = 8, message = "Flight code must be between 4 and 8 characters.")
        String code,
        @NotBlank(message = "Gate is required.")
        @NotNull(message = "Departure scheduled time is required.")
        LocalDateTime departureScheduled,
        @NotNull(message = "Arrival scheduled time is required.")
        LocalDateTime arrivalScheduled,
        @NotBlank(message = "Aircraft ID is required.")
        String aircraftId,
        @NotNull(message = "Route ID is required.")
        Long routeId
) {
}
