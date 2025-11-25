package com.example.airportManager.service;

import com.example.airportManager.dto.passenger.PassengerCreateDTO;
import com.example.airportManager.dto.passenger.PassengerResponseDTO;
import com.example.airportManager.dto.passenger.PassengerUpdateDTO;
import com.example.airportManager.model.Passenger;

public interface PassengerService {
    Passenger getByID(Long id);
    PassengerResponseDTO create(PassengerCreateDTO passengerCreateDTO);
    PassengerResponseDTO update(Long id, PassengerUpdateDTO passengerUpdateDTO);
}
