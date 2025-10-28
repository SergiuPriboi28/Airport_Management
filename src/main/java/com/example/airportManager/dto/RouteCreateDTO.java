package com.example.airportManager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RouteCreateDTO(
        @NotNull Long originAirportId,
        @NotNull Long destAirportId,
        @NotBlank int distanceNm,
        @NotBlank int stdDurationMin
) {
}
