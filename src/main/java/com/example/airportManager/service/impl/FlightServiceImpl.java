package com.example.airportManager.service.impl;

import com.example.airportManager.dto.FlightCreateDTO;
import com.example.airportManager.dto.FlightResponseDTO;
import com.example.airportManager.dto.FlightUpdateDTO;
import com.example.airportManager.mapper.FlightMapper;
import com.example.airportManager.model.Flight;
import com.example.airportManager.repository.FlightRepository;
import com.example.airportManager.service.FlightService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Override
    public Flight getById(Long id) {
        Flight flight = flightRepository.findById(id).
                orElseThrow(()->new RuntimeException("No Flight Found"));
        return  flight;
    }

    @Override
    public List<FlightResponseDTO> getAll(String sortBy, String dir) {

        Sort.Direction direction = dir.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return flightRepository.findAll(Sort.by(direction, sortBy)).
                stream().map(flightMapper::toResponse).toList();
    }

    @Override
    public FlightResponseDTO create(FlightCreateDTO flightCreateDTO) {
        Flight flight = flightMapper.toEntity(flightCreateDTO);
        Flight flightSave = flightRepository.save(flight);
        return flightMapper.toResponse(flightSave);
    }

    @Override
    public FlightResponseDTO update(Long id, FlightUpdateDTO flightUpdateDTO) {
        Flight oldFlight = flightRepository.findById(id).
                orElseThrow(()->new RuntimeException("No Flight Found"));
        flightMapper.updateFlightFromDTO(flightUpdateDTO, oldFlight);
        Flight updatedFlight = flightRepository.save(oldFlight);
        return flightMapper.toResponse(updatedFlight);
    }

    @Override
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }
}
