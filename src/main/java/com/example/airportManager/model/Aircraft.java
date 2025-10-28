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

    @Column
    private String tailNumber;

    @Column
    private String model;

    @Column
    private int capacity;

    @Column
    private String seatMapRef;

    @Column
    private String status;

    @OneToMany(mappedBy = "aircraft")
    private Set<Flight> flights;


}
