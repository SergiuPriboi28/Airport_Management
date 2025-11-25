package com.example.airportManager.service.impl;

import com.example.airportManager.dto.airport.AirportCreateDTO;
import com.example.airportManager.dto.airport.AirportResponseDTO;
import com.example.airportManager.mapper.AirportMapper;
import com.example.airportManager.model.Airport;
import com.example.airportManager.repository.AirportRepository;
import com.example.airportManager.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository, AirportMapper airportMapper) {
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
    }

    @Override
    public Airport getByID(Long id) {
        Airport airport = airportRepository.findById(id).
                orElseThrow(()-> new RuntimeException("No Aiport Found"));
        return airport;
    }

    @Override
    public Page<AirportResponseDTO> getAll(Pageable pageable) {
        return airportRepository.findAll(pageable)
                .map(airportMapper::toResponse);
    }


    @Override
    public AirportResponseDTO create(AirportCreateDTO airportCreateDTO) {
        Airport airport = airportMapper.toEntity(airportCreateDTO);
        Airport airportSave = airportRepository.save(airport);
        System.out.println("Airport entity AFTER save: " + airportSave);
        return airportMapper.toResponse(airportSave);
    }

//    @Override
//    public AirportResponseDTO update(Long id, AirportUpdateDTO airportUpdateDTO) {
//        Airport oldAirport = airportRepository.findById(id).
//                orElseThrow(()-> new RuntimeException("No Aiport Found"));
//        airportMapper.update(airportUpdateDTO, oldAirport);
//        Airport updatedAirport = airportRepository.save(oldAirport);
//        return airportMapper.toResponse(updatedAirport);
//    }

    @Override
    public void delete(Long id) {
        airportRepository.deleteById(id);
    }
}
