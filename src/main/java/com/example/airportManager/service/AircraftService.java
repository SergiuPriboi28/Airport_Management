package com.example.airportManager.service;

import com.example.airportManager.dto.aircraft.AircraftCreateDTO;
import com.example.airportManager.dto.aircraft.AircraftResponseDTO;
import com.example.airportManager.dto.aircraft.AircraftUpdateDTO;
import com.example.airportManager.model.Aircraft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AircraftService{
    Aircraft getById(Long id);
    Page<AircraftResponseDTO> getAll(Pageable pageable);
    AircraftResponseDTO create(AircraftCreateDTO aircraftCreateDTO);
    AircraftResponseDTO update(Long id, AircraftUpdateDTO aircraftUpdateDTO);
    void delete(Long id);
}
