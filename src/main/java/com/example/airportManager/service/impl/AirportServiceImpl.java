package com.example.airportManager.service.impl;

import com.example.airportManager.dto.AirportCreateDTO;
import com.example.airportManager.dto.AirportResponseDTO;
import com.example.airportManager.dto.AirportUpdateDTO;
import com.example.airportManager.mapper.AirportMapper;
import com.example.airportManager.model.Airport;
import com.example.airportManager.repository.AirportRepository;
import com.example.airportManager.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Override
    public Airport getByID(Long id) {
        Airport airport = airportRepository.findById(id).
                orElseThrow(()-> new RuntimeException("No Aiport Found"));
        return airport;
    }



    @Override
    public List<AirportResponseDTO> getAll(String sortBy, String dir) {
            Sort.Direction direction = dir.equalsIgnoreCase("desc")
                    ? Sort.Direction.DESC
                    : Sort.Direction.ASC;
            return airportRepository.findAll(Sort.by(direction, sortBy))
                    .stream().map(airportMapper::toResponse).toList();
        }

    @Override
    public AirportResponseDTO create(AirportCreateDTO airportCreateDTO) {
        Airport airport = airportMapper.toEntity(airportCreateDTO);
        Airport airportSave = airportRepository.save(airport);
        return airportMapper.toResponse(airportSave);
    }

    @Override
    public AirportResponseDTO update(Long id, AirportUpdateDTO airportUpdateDTO) {
        Airport oldAirport = airportRepository.findById(id).
                orElseThrow(()-> new RuntimeException("No Aiport Found"));
        airportMapper.updateAirportFromDTO(airportUpdateDTO, oldAirport);
        Airport updatedAirport = airportRepository.save(oldAirport);
        return airportMapper.toResponse(updatedAirport);
    }

    @Override
    public void delete(Long id) {
        airportRepository.deleteById(id);
    }
}
