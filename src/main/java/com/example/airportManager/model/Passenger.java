//package com.example.airportManager.model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//
//import java.util.Set;
//
//@Entity
//@Table(name = "passenger", indexes = {
//        @Index(name = "idx_passenger_doc", columnList = "docNumber")
//})
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString(exclude = {"bookings"})
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
////PassengerProfile(user_id, docType, docNumber, nationality, loyaltyTier, emergencyContact)
//public class Passenger {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @EqualsAndHashCode.Include
//    private Long id;
//
//    @Column
//    @NotBlank(message = "First name is required.")
//    private String firstName;
//
//    @Column
//    @NotBlank(message = "Last name is required.")
//    private String lastName;
//
//    @Column
//    @NotBlank(message = "Email is required.")
//    @Email(message = "Email must be a valid format.")
//    private String email;
//
//    @Column
//    @NotBlank(message = "Document type is required.")
//    private String docType;
//
//    @Column
//    @NotBlank(message = "Document number is required.")
//    @Size(min = 5, max = 20, message = "Document number must be between 5 and 20 characters.")
//    private String docNumber;
//
//    @Column
//    @NotBlank(message = "Nationality is required.")
//    private String nationality;
//
//    @Column
//    private String loyaltyTier;
//
//    @Column
//    private String emergencyContact;
//
//    @OneToMany(mappedBy = "passenger", fetch = FetchType.LAZY)
//    private Set<Booking> bookings;
//
//
//
//}
