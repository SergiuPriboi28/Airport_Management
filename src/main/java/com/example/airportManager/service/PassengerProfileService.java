package com.example.airportManager.service;

import com.example.airportManager.dto.passenger.PassengerProfileCreateDTO;
import com.example.airportManager.dto.passenger.PassengerProfileResponseDTO;
import com.example.airportManager.dto.passenger.PassengerProfileUpdateDTO;
import com.example.airportManager.model.PassengerProfile;

import java.util.UUID;

public interface PassengerProfileService {
    PassengerProfile getByID(UUID id);
    PassengerProfileResponseDTO create(PassengerProfileCreateDTO passengerProfileCreateDTO);
    PassengerProfileResponseDTO update(Long id, PassengerProfileUpdateDTO passengerProfileUpdateDTO);
}
