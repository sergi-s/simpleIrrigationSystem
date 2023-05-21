package com.simpleIrrigation.simpleIrrigationSystem.TimeSlot;

import com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand.PlotOfLand;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plot_of_land_id")
    private PlotOfLand plotOfLand;
    @Temporal( TemporalType.TIMESTAMP )
    private Date fromTime;
    @Temporal( TemporalType.TIMESTAMP )
    private Date toTime;
    private double amount; // assuming amount in liters

    public TimeSlot(){}

    public TimeSlot(Long id, PlotOfLand plotOfLand, Date fromTime, Date toTime, double amount) {
        this.id = id;
        this.plotOfLand = plotOfLand;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.amount = amount;
    }

//    public TimeSlot(PlotOfLand plotOfLand, LocalDateTime fromTime, LocalDateTime toTime, double amount) {
//        this.plotOfLand = plotOfLand;
//        this.fromTime = fromTime;
//        this.toTime = toTime;
//        this.amount = amount;
//    }

    public PlotOfLand getPlotOfLand() {
        return plotOfLand;
    }

    public void setPlotOfLand(PlotOfLand plotOfLand) {
        this.plotOfLand = plotOfLand;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "id=" + id +
                ", plotOfLand=" + plotOfLand +
                ", fromTime=" + fromTime +
                ", toTime=" + toTime +
                ", amount=" + amount +
                '}';
    }
}
