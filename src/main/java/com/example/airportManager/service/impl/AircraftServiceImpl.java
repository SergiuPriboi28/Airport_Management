package com.example.airportManager.service.impl;

import com.example.airportManager.dto.aircraft.AircraftCreateDTO;
import com.example.airportManager.dto.aircraft.AircraftResponseDTO;
import com.example.airportManager.dto.aircraft.AircraftUpdateDTO;
import com.example.airportManager.mapper.AircraftMapper;
import com.example.airportManager.model.Aircraft;
import com.example.airportManager.repository.AircraftRepository;
import com.example.airportManager.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;
    private final AircraftMapper aircraftMapper;

    @Autowired
    public AircraftServiceImpl(AircraftRepository aircraftRepository, AircraftMapper aircraftMapper) {
        this.aircraftRepository = aircraftRepository;
        this.aircraftMapper = aircraftMapper;
    }

    @Override
    public Aircraft getById(Long id) {
        return aircraftRepository.findById(id).
                orElseThrow(()->new RuntimeException("No Aircraft Found"));
    }

    @Override
    public Page<AircraftResponseDTO> getAll(Pageable pageable) {
        return aircraftRepository.findAll(pageable)
                .map(aircraftMapper::toResponse);
    }

    @Override
    public AircraftResponseDTO create(AircraftCreateDTO aircraftCreateDTO) {
        Aircraft aircraft = aircraftMapper.toEntity(aircraftCreateDTO);
        Aircraft aircraftSave = aircraftRepository.save(aircraft);
        return aircraftMapper.toResponse(aircraftSave);
    }

    @Override
    public AircraftResponseDTO update(Long id, AircraftUpdateDTO aircraftUpdateDTO) {
        Aircraft oldAircraft = aircraftRepository.findById(id).
                orElseThrow(()-> new RuntimeException("No Aircraft Found"));
        aircraftMapper.updateAircraftFromDTO(aircraftUpdateDTO, oldAircraft);
        Aircraft updatedAircraft = aircraftRepository.save(oldAircraft);
        return aircraftMapper.toResponse(updatedAircraft);
    }

    @Override
    public void delete(Long id) {
        aircraftRepository.deleteById(id);
    }
}
