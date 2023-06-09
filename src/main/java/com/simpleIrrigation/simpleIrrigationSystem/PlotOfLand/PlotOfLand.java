package com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand;

import com.simpleIrrigation.simpleIrrigationSystem.Device.Device;
import jakarta.persistence.*;

@Table
@Entity
public class PlotOfLand {

    @Id
    @SequenceGenerator(
            name = "plot_of_land_sequence",
            sequenceName = "plot_of_land_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "plot_of_land_sequence_sequence"
    )
    private long id;

    private String name;
    private double area;
    // any additional data

    @OneToOne(mappedBy = "plotOfLand")
    private Device device;

    public PlotOfLand(){

    }

    public PlotOfLand(long id , String name, double area) {
        this.name = name;
        this.area = area;
    }

    public PlotOfLand(String name, double area, Device device) {
        this.name = name;
        this.area = area;
        this.device = device;
    }

    public PlotOfLand(long id, String name, double area, Device device) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "PlotOfLand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", area=" + area +
                '}';
    }
}