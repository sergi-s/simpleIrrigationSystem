package com.simpleIrrigation.simpleIrrigationSystem.Device;

import jakarta.persistence.*;


import com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand.PlotOfLand;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "plot_of_land_id")
    private PlotOfLand plotOfLand;

    // Constructors, getters, and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlotOfLand getPlotOfLand() {
        return plotOfLand;
    }

    public void setPlotOfLand(PlotOfLand plotOfLand) {
        this.plotOfLand = plotOfLand;
    }

    public Device(PlotOfLand plotOfLand) {
        this.plotOfLand = plotOfLand;
    }

    public Device(Long id, PlotOfLand plotOfLand) {
        this.id = id;
        this.plotOfLand = plotOfLand;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", plotOfLand=" + plotOfLand +
                '}';
    }
}
