package com.simpleIrrigation.simpleIrrigationSystem.SensorDevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id).orElse(null);
    }

    public Device createDevice( Device device) {
        return deviceRepository.save(device);
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
