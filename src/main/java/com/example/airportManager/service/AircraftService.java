package com.example.airportManager.service;

import com.example.airportManager.dto.AircraftCreateDTO;
import com.example.airportManager.dto.AircraftResponseDTO;
import com.example.airportManager.dto.AircraftUpdateDTO;
import com.example.airportManager.model.Aircraft;
import com.example.airportManager.repository.AircraftRepository;

import java.util.List;

public interface AircraftService{
    Aircraft getById(Long id);
    List<AircraftResponseDTO> getAll(String sortBy, String dir);
    AircraftResponseDTO create(AircraftCreateDTO aircraftCreateDTO);
    AircraftResponseDTO update(Long id, AircraftUpdateDTO aircraftUpdateDTO);
    void delete(Long id);
}
