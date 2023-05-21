package com.simpleIrrigation.simpleIrrigationSystem.Device;

import com.simpleIrrigation.simpleIrrigationSystem.Device.DTO.DeviceRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
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
    public Device createDevice(@RequestBody DeviceRequestDTO deviceRequestDTO) {
        return this.deviceService.createDevice(deviceRequestDTO);
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
