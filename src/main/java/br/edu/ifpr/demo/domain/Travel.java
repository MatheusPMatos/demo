package br.edu.ifpr.demo.domain;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(optional = false)
    Passenger passenger;

    @ManyToOne
    Driver driver;

    String origin;
    String destination;

    @Enumerated(EnumType.STRING)
    TravelStatus status;
}