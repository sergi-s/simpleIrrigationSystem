package com.simpleIrrigation.simpleIrrigationSystem.TimeSlot.DTO;

import java.time.LocalDateTime;
import java.util.Date;

public class TimeSlotRequestDTO {

    private Long plotOfLandId;

    private Date fromTime;

    private Date toTime;

    private Double amount;

    public Long getPlotOfLandId() {
        return plotOfLandId;
    }

    public void setPlotOfLandId(Long plotOfLandId) {
        this.plotOfLandId = plotOfLandId;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    TimeSlotRequestDTO(){

    }
    public TimeSlotRequestDTO(Long plotOfLandId, Date fromTime, Date toTime, Double amount) {
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

