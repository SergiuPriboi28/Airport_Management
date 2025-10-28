package com.example.airportManager.dto;

import com.example.airportManager.model.BookingStatus;

public record BookingResponseDTO(
        String pnr,
        String flight,
        String passenger,
        BookingStatus bookingStatus
) {
}
