//package com.example.airportManager.service.impl;
//
//import com.example.airportManager.dto.passenger.PassengerProfileCreateDTO;
//import com.example.airportManager.dto.passenger.PassengerProfileResponseDTO;
//import com.example.airportManager.dto.passenger.PassengerProfileUpdateDTO;
//import com.example.airportManager.mapper.PassengerMapper;
//import com.example.airportManager.mapper.PassengerProfileMapper;
//import com.example.airportManager.model.Passenger;
//import com.example.airportManager.model.PassengerProfile;
//import com.example.airportManager.repository.PassengerProfileRepository;
//import com.example.airportManager.repository.PassengerRepository;
//import com.example.airportManager.service.PassengerProfileService;
//import com.example.airportManager.service.PassengerService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class PassengerServiceImpl implements PassengerProfileService {
//
//    private final PassengerProfileRepository passengerProfileRepository;
//    private final PassengerProfileMapper passengerProfileMapper;
//
//    @Override
//    public Optional<PassengerProfile> getByID(UUID id) {
//        Optional<PassengerProfile> passenger = passengerProfileRepository.findById(id);
//        return passenger;
//    }
//
//    public PassengerProfileResponseDTO create(PassengerProfileCreateDTO passengerProfileCreateDTO){
//        PassengerProfile passenger = passengerProfileMapper.toEntity(passengerProfileCreateDTO);
//        PassengerProfile passengerSave = passengerProfileRepository.save(passenger);
//
//        return passengerProfileMapper.toResponse(passengerSave);
//
//    }
//
//    @Override
//    public PassengerProfileResponseDTO update(Long id, PassengerProfileUpdateDTO passengerProfileUpdateDTO) {
//        return null;
//    }
//
//}
