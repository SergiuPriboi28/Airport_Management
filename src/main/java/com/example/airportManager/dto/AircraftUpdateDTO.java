package com.example.airportManager.dto;

public record AircraftUpdateDTO(
        String tailNumber,
        String seatMapRef,
        String status,
        int capacity
) {
}
