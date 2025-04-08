package com.cjs.example.sharding.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_passenger")
public class PassengerEntity implements Serializable {

    @Id
    @Column(name = "passenger_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerId;

    private Long orderId;

    private Long orderItemId;

    private String passengerName;

    private Integer passengerIdType;

    private String passengerIdNo;
}
