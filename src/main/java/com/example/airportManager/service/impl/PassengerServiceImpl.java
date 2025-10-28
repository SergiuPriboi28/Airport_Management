package com.example.airportManager.service.impl;

import com.example.airportManager.dto.PassengerCreateDTO;
import com.example.airportManager.dto.PassengerResponseDTO;
import com.example.airportManager.dto.PassengerUpdateDTO;
import com.example.airportManager.mapper.PassengerMapper;
import com.example.airportManager.model.Passenger;
import com.example.airportManager.repository.PassengerRepository;
import com.example.airportManager.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    @Override
    public Passenger getByID(Long id) {
        Passenger passenger = passengerRepository.findById(id).orElseThrow(() ->
                new RuntimeException("No passenger found"));
        return passenger;
    }

    public PassengerResponseDTO create(PassengerCreateDTO passengerCreateDTO){
        Passenger passenger = passengerMapper.toEntity(passengerCreateDTO);
        Passenger passengerSave = passengerRepository.save(passenger);

        return passengerMapper.toResponse(passengerSave);

    }

    @Override
    public PassengerResponseDTO update(Long id, PassengerUpdateDTO passengerUpdateDTO) {
        return null;
    }

}
