package com.example.airportManager.dto.flight;

import com.example.airportManager.validation.ArrivalAfterDeparture;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@ArrivalAfterDeparture
public record FlightCreateDTO(

        String code,
        LocalDateTime departureScheduled,
        LocalDateTime arrivalScheduled,
        String aircraftId,
        Long routeId
) {
}
