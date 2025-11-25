package com.example.airportManager.dto.aircraft;

public record AircraftResponseDTO(
        String tailNumber,
        String model,
        String seatMapRef,
        String status,
        int capacity
) {
}
