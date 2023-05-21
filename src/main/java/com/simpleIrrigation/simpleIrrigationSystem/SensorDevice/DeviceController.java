package com.simpleIrrigation.simpleIrrigationSystem.SensorDevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return this.deviceService.getAllDevices();
    }

    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable Long id) {
        return this.deviceService.getDeviceById(id);
    }

    @PostMapping
    public Device createDevice(@RequestBody Device device) {
        return this.deviceService.createDevice(device);
    }

    @PutMapping("/{id}")
    public Device updateDevice(@PathVariable Long id, @RequestBody Device deviceDetails) {
        return this.deviceService.updateDevice(id, deviceDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable Long id) {
        this.deviceService.deleteDevice(id);
    }
}
