package com.example.airportManager.service;

import com.example.airportManager.dto.PassengerCreateDTO;
import com.example.airportManager.dto.PassengerResponseDTO;
import com.example.airportManager.dto.PassengerUpdateDTO;
import com.example.airportManager.model.Passenger;

public interface PassengerService {
    Passenger getByID(Long id);
    PassengerResponseDTO create(PassengerCreateDTO passengerCreateDTO);
    PassengerResponseDTO update(Long id, PassengerUpdateDTO passengerUpdateDTO);
}
