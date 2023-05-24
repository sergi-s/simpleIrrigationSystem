package com.simpleIrrigation.simpleIrrigationSystem.Device;

import com.simpleIrrigation.simpleIrrigationSystem.Device.DTO.DeviceRequestDTO;
import com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand.PlotOfLand;
import com.simpleIrrigation.simpleIrrigationSystem.PlotOfLand.PlotOfLandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeviceServiceTest {

    private DeviceService deviceService;

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private PlotOfLandRepository plotOfLandRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deviceService = new DeviceService(deviceRepository, plotOfLandRepository);
    }

    @Test
    void getAllDevices_shouldReturnListOfDevices() {
        // Arrange
        List<Device> expectedDevices = new ArrayList<>();
        when(deviceRepository.findAll()).thenReturn(expectedDevices);

        // Act
        List<Device> actualDevices = deviceService.getAllDevices();

        // Assert
        assertEquals(expectedDevices, actualDevices);
    }

    @Test
    void getDeviceById_existingId_shouldReturnDevice() {
        // Arrange
        Long deviceId = 1L;
        Device expectedDevice = new Device();
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(expectedDevice));

        // Act
        Device actualDevice = deviceService.getDeviceById(deviceId);

        // Assert
        assertEquals(expectedDevice, actualDevice);
    }

    @Test
    void getDeviceById_nonExistingId_shouldReturnNull() {
        // Arrange
        Long deviceId = 1L;
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());

        // Act
        Device actualDevice = deviceService.getDeviceById(deviceId);

        // Assert
        assertNull(actualDevice);
    }

    @Test
    void createDevice_validRequest_shouldReturnCreatedDevice() {
        // Arrange
        DeviceRequestDTO deviceRequestDTO = new DeviceRequestDTO();
        deviceRequestDTO.setId(1L);
        deviceRequestDTO.setPlotOfLandId(2L);
        PlotOfLand plotOfLand = new PlotOfLand();
        when(plotOfLandRepository.getById(deviceRequestDTO.getPlotOfLandId())).thenReturn(plotOfLand);
        when(deviceRepository.save(any(Device.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Device createdDevice = deviceService.createDevice(deviceRequestDTO);

        // Assert
        assertNotNull(createdDevice);
        assertEquals(deviceRequestDTO.getId(), createdDevice.getId());
        assertEquals(plotOfLand, createdDevice.getPlotOfLand());
    }

    @Test
    void createDevice_invalidPlotOfLandId_shouldThrowException() {
        // Arrange
        DeviceRequestDTO deviceRequestDTO = new DeviceRequestDTO();
        deviceRequestDTO.setPlotOfLandId(2L);
        when(plotOfLandRepository.getById(deviceRequestDTO.getPlotOfLandId())).thenReturn(null);

        // Act and Assert
        assertThrows(ResponseStatusException.class, () -> deviceService.createDevice(deviceRequestDTO));
    }

    @Test
    void updateDevice_existingId_shouldReturnUpdatedDevice() {
        // Arrange
        Long deviceId = 1L;
        Device existingDevice = new Device();
        existingDevice.setId(deviceId);
        Device deviceDetails = new Device();
        deviceDetails.setPlotOfLand(new PlotOfLand());
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(existingDevice));
        when(deviceRepository.save(any(Device.class))).thenAnswer(invocation -> invocation.getArgument(0));
        // Act
        Device updatedDevice = deviceService.updateDevice(deviceId, deviceDetails);

        // Assert
        assertNotNull(updatedDevice);
        assertEquals(existingDevice.getId(), updatedDevice.getId());
        assertEquals(deviceDetails.getPlotOfLand(), updatedDevice.getPlotOfLand());
    }

    @Test
    void updateDevice_nonExistingId_shouldReturnNull() {
        // Arrange
        Long deviceId = 1L;
        Device deviceDetails = new Device();
        deviceDetails.setPlotOfLand(new PlotOfLand());
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());

        // Act
        Device updatedDevice = deviceService.updateDevice(deviceId, deviceDetails);

        // Assert
        assertNull(updatedDevice);
    }

    @Test
    void deleteDevice_existingId_shouldInvokeDeleteById() {
        // Arrange
        Long deviceId = 1L;

        // Act
        deviceService.deleteDevice(deviceId);

        // Assert
        verify(deviceRepository, times(1)).deleteById(deviceId);
    }

    @Test
    void getDeviceByPlotOfLandId_existingId_shouldReturnDevice() {
        // Arrange
        Long plotOfLandId = 1L;
        Device expectedDevice = new Device();
        when(deviceRepository.findByPlotOfLandId(plotOfLandId)).thenReturn(expectedDevice);

        // Act
        Device actualDevice = deviceService.getDeviceByPlotOfLandId(plotOfLandId);

        // Assert
        assertEquals(expectedDevice, actualDevice);
    }

    @Test
    void getDeviceByPlotOfLandId_nonExistingId_shouldReturnNull() {
        // Arrange
        Long plotOfLandId = 1L;
        when(deviceRepository.findByPlotOfLandId(plotOfLandId)).thenReturn(null);

        // Act
        Device actualDevice = deviceService.getDeviceByPlotOfLandId(plotOfLandId);

        // Assert
        assertNull(actualDevice);
    }

    @Test
    void sendRequestToDevice_shouldPrintMessage() {
        // Arrange
        Device device = new Device();
        device.setId(1L);

        // Redirect System.out to a custom PrintStream
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        deviceService.sendRequestToDevice(device);

        // Assert
        String expectedMessage = "Sending request to device for plot ID: 1";
        assertEquals(expectedMessage, outContent.toString().trim());

        // Restore System.out
        System.setOut(originalOut);
    }
}
