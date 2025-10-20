package com.example.airportManager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"bookings"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//PassengerProfile(user_id, docType, docNumber, nationality, loyaltyTier, emergencyContact)
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String docType;

    private int docNumber;

    private String nationality;

    private String loyaltyTier;

    private String emergencyContact;

    @OneToMany(mappedBy = "passenger", fetch = FetchType.LAZY)
    private Set<Booking> bookings;



}
