package com.example.airportManager.service;

import com.example.airportManager.dto.FlightCreateDTO;
import com.example.airportManager.dto.FlightResponseDTO;
import com.example.airportManager.dto.FlightUpdateDTO;
import com.example.airportManager.model.Flight;

import java.util.List;

public interface FlightService {
    Flight getById(Long id);
    List<FlightResponseDTO> getAll(String sortBy, String dir);
    FlightResponseDTO create(FlightCreateDTO flightCreateDTO);
    FlightResponseDTO update(Long id, FlightUpdateDTO flightUpdateDTO);
    void delete(Long id);
}
