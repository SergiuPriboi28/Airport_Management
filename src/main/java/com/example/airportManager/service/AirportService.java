package com.example.airportManager.service;

import com.example.airportManager.dto.airport.AirportCreateDTO;
import com.example.airportManager.dto.airport.AirportResponseDTO;
import com.example.airportManager.model.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AirportService {
    Airport getByID(Long id);
    Page<AirportResponseDTO> getAll(Pageable pageable);
    AirportResponseDTO create(AirportCreateDTO airportCreateDTO);
//    AirportResponseDTO update(Long id, AirportUpdateDTO airportUpdateDTO);
    void delete(Long id);
}
