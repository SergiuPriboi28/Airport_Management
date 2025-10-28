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

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String docType;

    @Column
    private int docNumber;

    @Column
    private String nationality;

    @Column
    private String loyaltyTier;

    @Column
    private String emergencyContact;

    @OneToMany(mappedBy = "passenger", fetch = FetchType.LAZY)
    private Set<Booking> bookings;



}
