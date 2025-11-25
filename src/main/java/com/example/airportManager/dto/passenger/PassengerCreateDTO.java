package com.example.airportManager.dto.passenger;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PassengerCreateDTO(
        @NotBlank(message = "First name is required.") String firstName,
        @NotBlank(message = "Last name is required.") String lastName,
        @NotBlank(message = "Email is required.")
        @Email(message = "Email must be a valid format.") String email,
        @NotBlank(message = "Document type is required.") String docType,
        @NotBlank(message = "Document number is required.")
        @Size(min = 5, max = 20, message = "Document number must be between 5 and 20 characters.")
        String docNumber,
        @NotBlank(message = "Nationality is required.") String nationality
){}