package com.example.airportManager.service;

import com.example.airportManager.dto.passengerProfile.PassengerProfileCreateDTO;
import com.example.airportManager.dto.passengerProfile.PassengerProfileResponseDTO;
import com.example.airportManager.dto.passengerProfile.PassengerProfileUpdateDTO;
import com.example.airportManager.model.PassengerProfile;

import java.util.UUID;

public interface PassengerProfileService {
    PassengerProfile getByID(UUID id);
    PassengerProfileResponseDTO create(PassengerProfileCreateDTO passengerProfileCreateDTO);
    PassengerProfileResponseDTO update(UUID id, PassengerProfileUpdateDTO passengerProfileUpdateDTO);
}
