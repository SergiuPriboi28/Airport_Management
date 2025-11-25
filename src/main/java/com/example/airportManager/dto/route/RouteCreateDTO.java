package com.example.airportManager.dto.route;

import com.example.airportManager.validation.DifferentAirports;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@DifferentAirports
public record RouteCreateDTO(
        @NotNull(message = "Origin airport ID is required.") Long originAirportId,
        @NotNull(message = "Destination airport ID is required.") Long destAirportId,
        @Min(value = 1, message = "Distance must be a positive value.") int distanceNm,
        @Min(value = 1, message = "Duration must be a positive value.") int stdDurationMin
) {
}
