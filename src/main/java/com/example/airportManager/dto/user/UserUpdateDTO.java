package com.example.airportManager.dto.user;

import com.example.airportManager.model.EmployeeProfile;
import com.example.airportManager.model.PassengerProfile;
import com.example.airportManager.model.UserStatus;

import java.util.Optional;

public record UserUpdateDTO(
        String email,
        String phone,
        UserStatus status,
        Optional<EmployeeProfile> employeeProfile,
        Optional<PassengerProfile> passengerProfile
) {
}
