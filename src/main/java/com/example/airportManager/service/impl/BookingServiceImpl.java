package com.example.airportManager.service.impl;

import com.example.airportManager.dto.BookingCreateDTO;
import com.example.airportManager.dto.BookingResponseDTO;
import com.example.airportManager.dto.BookingUpdateDTO;
import com.example.airportManager.mapper.BookingMapper;
import com.example.airportManager.model.Booking;
import com.example.airportManager.repository.BookingRepository;
import com.example.airportManager.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    public Booking getById(Long id) {
        Booking booking = bookingRepository.findById(id).
                orElseThrow(()->new RuntimeException("No Booking Found"));
        return booking;
    }

    @Override
    public List<BookingResponseDTO> getAll(String sortBy, String dir) {
        Sort.Direction direction = dir.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        return bookingRepository.findAll(Sort.by(direction, sortBy))
                .stream().map(bookingMapper::toResponse).toList();
    }

    @Override
    public BookingResponseDTO create(BookingCreateDTO bookingCreateDTO) {
        Booking booking = bookingMapper.toEntity(bookingCreateDTO);
        Booking bookingSave = bookingRepository.save(booking);
        return bookingMapper.toResponse(bookingSave);
    }

    @Override
    public BookingResponseDTO update(Long id, BookingUpdateDTO bookingUpdateDTO) {
        Booking oldBooking = bookingRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Booking Not Found"));
        bookingMapper.updateBookingFromDto(bookingUpdateDTO, oldBooking);
        Booking updatedBooking = bookingRepository.save(oldBooking);
        return bookingMapper.toResponse(updatedBooking);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }
}
