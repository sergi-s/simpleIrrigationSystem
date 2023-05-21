package com.simpleIrrigation.simpleIrrigationSystem.SensorDevice;

import jakarta.persistence.*;


import com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand.PlotOfLand;
import jakarta.persistence.*;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "plot_of_land_id")
    private PlotOfLand plotOfLand;

    // Constructors, getters, and setters
}
