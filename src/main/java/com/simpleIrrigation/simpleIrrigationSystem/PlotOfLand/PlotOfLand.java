package com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand;

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

    public PlotOfLand(){

    }
    public PlotOfLand(String name, double area) {
        this.name = name;
        this.area = area;
    }

    public PlotOfLand(long id, String name, double area) {
        this.id = id;
        this.name = name;
        this.area = area;
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