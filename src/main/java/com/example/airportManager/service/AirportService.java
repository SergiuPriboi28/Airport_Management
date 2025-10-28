package com.example.airportManager.service;

import com.example.airportManager.dto.AirportCreateDTO;
import com.example.airportManager.dto.AirportResponseDTO;
import com.example.airportManager.dto.AirportUpdateDTO;
import com.example.airportManager.model.Airport;

import java.util.List;

public interface AirportService {
    Airport getByID(Long id);
    List<AirportResponseDTO> getAll(String sortBy, String dir);
    AirportResponseDTO create(AirportCreateDTO airportCreateDTO);
    AirportResponseDTO update(Long id, AirportUpdateDTO airportUpdateDTO);
    void delete(Long id);
}
