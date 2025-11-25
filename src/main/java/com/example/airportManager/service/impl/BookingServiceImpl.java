package com.example.airportManager.service.impl;

import com.example.airportManager.dto.booking.BookingCreateDTO;
import com.example.airportManager.dto.booking.BookingResponseDTO;
import com.example.airportManager.dto.booking.BookingUpdateDTO;
import com.example.airportManager.mapper.BookingMapper;
import com.example.airportManager.model.Booking;
import com.example.airportManager.model.Flight;
import com.example.airportManager.model.Passenger;
import com.example.airportManager.repository.BookingRepository;
import com.example.airportManager.repository.FlightRepository;
import com.example.airportManager.repository.PassengerRepository;
import com.example.airportManager.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;

    @Override
    @Transactional(readOnly = true)
    public Booking getById(Long id) {
        Booking booking = bookingRepository.findById(id).
                orElseThrow(()->new RuntimeException("No Booking Found"));
        return booking;
    }

    @Override
    public Page<BookingResponseDTO> getAll(Pageable pageable) {
        return bookingRepository.findAll(pageable)
                .map(bookingMapper::toResponse);
    }

    @Override
    @Transactional
    public BookingResponseDTO create(BookingCreateDTO bookingCreateDTO) {

        Passenger passenger = passengerRepository.findById(bookingCreateDTO.passengerId())
                .orElseThrow(() -> new RuntimeException("Passenger with id: "
                        + bookingCreateDTO.passengerId()
                        + " does not exist"));
        Flight flight = flightRepository.findById(bookingCreateDTO.flightId())
                .orElseThrow(() -> new RuntimeException("Flight with id: "
                        + bookingCreateDTO.flightId()
                        + " does not exist"));


        Booking booking = bookingMapper.toEntity(bookingCreateDTO);

        booking.setPassenger(passenger);
        booking.setFlight(flight);
        booking.setPnr(generatePNR());


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

    private String generatePNR() {
        return "PNR" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

}
