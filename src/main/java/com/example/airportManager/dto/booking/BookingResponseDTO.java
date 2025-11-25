package com.example.airportManager.dto.booking;

import com.example.airportManager.model.BookingStatus;

public record BookingResponseDTO(
        String pnr,
        String flightId,
        String passengerId,
        BookingStatus bookingStatus
) {
}
