package com.example.airportManager.controller;

import com.example.airportManager.dto.airport.AirportResponseDTO;
import com.example.airportManager.dto.booking.BookingCreateDTO;
import com.example.airportManager.dto.booking.BookingResponseDTO;
import com.example.airportManager.dto.booking.BookingUpdateDTO;
import com.example.airportManager.model.Booking;
import com.example.airportManager.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
@Validated
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<Booking> findBookingById(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getById(id));
    }

    @GetMapping
    public Page<BookingResponseDTO> getAll(
            @ParameterObject @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ){
        return bookingService.getAll(pageable);
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> create (
            @RequestBody BookingCreateDTO bookingCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(bookingService.create(bookingCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> update(
            @PathVariable Long id,
            @RequestBody BookingUpdateDTO bookingUpdateDTO) {
        BookingResponseDTO updatedBooking = bookingService.update(id, bookingUpdateDTO);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        bookingService.delete(id);
        return ResponseEntity.noContent().build();
    }

}