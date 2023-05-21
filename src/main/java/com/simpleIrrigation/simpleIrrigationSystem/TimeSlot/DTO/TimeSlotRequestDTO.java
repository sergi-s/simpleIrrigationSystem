package com.simpleIrrigation.simpleIrrigationSystem.TimeSlot.DTO;

import java.time.LocalDateTime;

public class TimeSlotRequestDTO {

    private Long plotOfLandId;

    private LocalDateTime fromTime;

    private LocalDateTime toTime;

    private Double amount;

    public Long getPlotOfLandId() {
        return plotOfLandId;
    }

    public void setPlotOfLandId(Long plotOfLandId) {
        this.plotOfLandId = plotOfLandId;
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalDateTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalDateTime toTime) {
        this.toTime = toTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    TimeSlotRequestDTO(){

    }
    public TimeSlotRequestDTO(Long plotOfLandId, LocalDateTime fromTime, LocalDateTime toTime, Double amount) {
        this.plotOfLandId = plotOfLandId;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TimeSlotRequestDTO{" +
                "plotOfLandId=" + plotOfLandId +
                ", fromTime=" + fromTime +
                ", toTime=" + toTime +
                ", amount=" + amount +
                '}';
    }
}

