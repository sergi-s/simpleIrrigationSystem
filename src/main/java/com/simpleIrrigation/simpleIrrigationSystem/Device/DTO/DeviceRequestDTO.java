package com.simpleIrrigation.simpleIrrigationSystem.Device.DTO;

public class DeviceRequestDTO {

    private Long id;

    private Long plotOfLandId;

    public DeviceRequestDTO(){}
    public DeviceRequestDTO(Long id, Long plotOfLandId) {
        this.id = id;
        this.plotOfLandId = plotOfLandId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlotOfLandId() {
        return plotOfLandId;
    }

    public void setPlotOfLandId(Long plotOfLandId) {
        this.plotOfLandId = plotOfLandId;
    }
}
