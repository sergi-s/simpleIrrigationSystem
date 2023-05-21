package com.simpleIrrigation.simpleIrrigationSystem.Device;

import com.simpleIrrigation.simpleIrrigationSystem.Device.DTO.DeviceRequestDTO;
import com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand.PlotOfLand;
import com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand.PlotOfLandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final PlotOfLandRepository plotOfLandRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, PlotOfLandRepository plotOfLandRepository) {
        this.deviceRepository = deviceRepository;
        this.plotOfLandRepository = plotOfLandRepository;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id).orElse(null);
    }

    public Device createDevice(DeviceRequestDTO deviceRequestDTO) {

        Optional<PlotOfLand> optionalPlotOfLand = this.plotOfLandRepository.findById(deviceRequestDTO.getPlotOfLandId());

        if (optionalPlotOfLand.isPresent()) {
            PlotOfLand plotOfLand = optionalPlotOfLand.get();
            Device device = new Device();
            device.setId(deviceRequestDTO.getId());
            device.setPlotOfLand(plotOfLand);

            return this.deviceRepository.save(device);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PlotOfLand not found");
        }
    }

    public Device updateDevice( Long id, Device deviceDetails) {
        Device device = deviceRepository.findById(id).orElse(null);
        if (device != null) {
            device.setPlotOfLand(deviceDetails.getPlotOfLand());
            return deviceRepository.save(device);
        }
        return null;
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }
}
