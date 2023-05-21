package com.simpleIrrigation.simpleIrrigationSystem.SensorDevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable Long id) {
        return deviceRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Device createDevice(@RequestBody Device device) {
        return deviceRepository.save(device);
    }

    @PutMapping("/{id}")
    public Device updateDevice(@PathVariable Long id, @RequestBody Device deviceDetails) {
        Device device = deviceRepository.findById(id).orElse(null);
        if (device != null) {
            device.setPlotOfLand(deviceDetails.getPlotOfLand());
            return deviceRepository.save(device);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable Long id) {
        deviceRepository.deleteById(id);
    }
}
