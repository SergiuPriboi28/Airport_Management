package com.example.airportManager.service;

import com.example.airportManager.dto.BookingCreateDTO;
import com.example.airportManager.dto.BookingResponseDTO;
import com.example.airportManager.dto.BookingUpdateDTO;
import com.example.airportManager.model.Booking;

import java.util.List;

public interface BookingService {
    Booking getById(Long id);
    List<BookingResponseDTO> getAll(String sortBy, String dir);
    BookingResponseDTO create(BookingCreateDTO bookingCreateDTO);
    BookingResponseDTO update(Long id, BookingUpdateDTO bookingUpdateDTO);
    void delete(Long id);
}
