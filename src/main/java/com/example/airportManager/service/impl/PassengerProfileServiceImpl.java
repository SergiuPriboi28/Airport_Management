package com.example.airportManager.service.impl;

import com.example.airportManager.dto.passengerProfile.PassengerProfileCreateDTO;
import com.example.airportManager.dto.passengerProfile.PassengerProfileResponseDTO;
import com.example.airportManager.dto.passengerProfile.PassengerProfileUpdateDTO;
import com.example.airportManager.mapper.PassengerProfileMapper;
import com.example.airportManager.model.PassengerProfile;
import com.example.airportManager.repository.PassengerProfileRepository;
import com.example.airportManager.service.PassengerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PassengerProfileServiceImpl implements PassengerProfileService {

    private final PassengerProfileRepository passengerProfileRepository;
    private final PassengerProfileMapper passengerProfileMapper;

    @Override
    public PassengerProfile getByID(UUID id) {
        PassengerProfile passenger = passengerProfileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No Passenger Profile Found!"));
        return passenger;
    }

    public PassengerProfileResponseDTO create(PassengerProfileCreateDTO passengerProfileCreateDTO){
        PassengerProfile passenger = passengerProfileMapper.toEntity(passengerProfileCreateDTO);
        PassengerProfile passengerSave = passengerProfileRepository.save(passenger);

        return passengerProfileMapper.toResponse(passengerSave);

    }

    @Override
    public PassengerProfileResponseDTO update(UUID id, PassengerProfileUpdateDTO passengerProfileUpdateDTO) {
        PassengerProfile oldProfile = passengerProfileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No Passenger Profile Found!"));
        passengerProfileMapper.updatePassengerProfileFromDTO(passengerProfileUpdateDTO, oldProfile);
        PassengerProfile updatedPassengerProfile = passengerProfileRepository.save(oldProfile);
        return passengerProfileMapper.toResponse(updatedPassengerProfile);
    }

}

