package com.example.airportManager.controller;

import com.example.airportManager.dto.BookingCreateDTO;
import com.example.airportManager.dto.BookingResponseDTO;
import com.example.airportManager.dto.BookingUpdateDTO;
import com.example.airportManager.model.Booking;
import com.example.airportManager.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<Booking> findBookingById(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAll(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String dir){
        List<BookingResponseDTO> bookingList = bookingService.getAll(sortBy, dir);
        return ResponseEntity.ok(bookingList);
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