package com.example.airportManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "booking", indexes = {
        @Index(name = "idx_booking_pnr", columnList = "pnr")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//Booking(id, user_id, flight_id, pnr, bookingStatus: CONFIRMED|CANCELLED|CHECKED_IN)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, nullable = false)
    private String pnr;

    @Enumerated(EnumType.STRING)
    @NotNull
    private BookingStatus status;

    @NotNull(message = "Flight ID is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "flight_id"
    )
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_profile_id", nullable = false)
    private PassengerProfile passengerProfile;

}
