package com.example.airportManager.dto.aircraft;

public record AircraftUpdateDTO(
        String tailNumber,
        String seatMapRef,
        String status,
        int capacity
) {
}
