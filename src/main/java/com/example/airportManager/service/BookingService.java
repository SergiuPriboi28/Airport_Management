package com.example.airportManager.service;

import com.example.airportManager.dto.booking.BookingCreateDTO;
import com.example.airportManager.dto.booking.BookingResponseDTO;
import com.example.airportManager.dto.booking.BookingUpdateDTO;
import com.example.airportManager.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookingService {
    Booking getById(Long id);
    Page<BookingResponseDTO> getAll(Pageable pageable);
    BookingResponseDTO create(BookingCreateDTO bookingCreateDTO);
    BookingResponseDTO update(Long id, BookingUpdateDTO bookingUpdateDTO);
    void delete(Long id);
}
