package com.example.airportManager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PassengerResponseDTO( String firstName,
                                    String lastName,
                                    String email,
                                    String docType,
                                    int docNumber,
                                    String nationality) {
}
