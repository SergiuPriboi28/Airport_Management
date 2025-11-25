package com.example.airportManager.dto.route;

public record RouteResponseDTO(
        String originAirportId,
        String destAirportId,
        int distanceNm,
        int stdDurationMin
) {
}
