package com.example.airportManager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"flights"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//Aircraft(id, tailNumber, model, capacity, seatMapRef, status)
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long ID;

    private String tailNumber;

    private String model;

    private int capacity;

    private String seatMapRef;

    private String status;

    @OneToMany(mappedBy = "aircraft")
    private Set<Flight> flights;


}
