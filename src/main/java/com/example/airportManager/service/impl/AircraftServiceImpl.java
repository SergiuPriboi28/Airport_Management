package com.example.airportManager.service.impl;

import com.example.airportManager.dto.AircraftCreateDTO;
import com.example.airportManager.dto.AircraftResponseDTO;
import com.example.airportManager.dto.AircraftUpdateDTO;
import com.example.airportManager.mapper.AircraftMapper;
import com.example.airportManager.model.Aircraft;
import com.example.airportManager.repository.AircraftRepository;
import com.example.airportManager.service.AircraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;
    private final AircraftMapper aircraftMapper;

    @Override
    public Aircraft getById(Long id) {
        return aircraftRepository.findById(id).
                orElseThrow(()->new RuntimeException("No Aircraft Found"));
    }

    @Override
    public List<AircraftResponseDTO> getAll(String sortBy, String dir) {
        Sort.Direction direction = dir.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        return aircraftRepository.findAll(Sort.by(direction, sortBy))
                .stream().map(aircraftMapper::toResponse).toList();
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
