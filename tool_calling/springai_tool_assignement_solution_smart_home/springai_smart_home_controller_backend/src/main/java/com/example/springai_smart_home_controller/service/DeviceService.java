package com.example.springai_smart_home_controller.service;


import com.example.springai_smart_home_controller.model.Device;
import com.example.springai_smart_home_controller.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    
    @Autowired
    private DeviceRepository deviceRepository;
    
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
    
    public List<Device> getDevicesByRoom(String room) {
        return deviceRepository.findByRoom(room);
    }
    
    public Optional<Device> getDeviceByName(String name) {
        return deviceRepository.findByNameIgnoreCase(name);
    }
    
    public Device saveDevice(Device device) {
        device.setLastUpdated(LocalDateTime.now());
        return deviceRepository.save(device);
    }
    
    public Device toggleDevice(String deviceName) {
        Optional<Device> deviceOpt = deviceRepository.findByNameIgnoreCase(deviceName);
        if (deviceOpt.isPresent()) {
            Device device = deviceOpt.get();
            device.setIsOn(!device.getIsOn());
            return deviceRepository.save(device);
        }
        throw new RuntimeException("Device not found: " + deviceName);
    }
    
    public Device updateDeviceValue(String deviceName, Double value) {
        Optional<Device> deviceOpt = deviceRepository.findByNameIgnoreCase(deviceName);
        if (deviceOpt.isPresent()) {
            Device device = deviceOpt.get();
            device.setValue(value);
            return deviceRepository.save(device);
        }
        throw new RuntimeException("Device not found: " + deviceName);
    }
    
    public Double getTotalEnergyConsumption() {
        Double total = deviceRepository.getTotalEnergyConsumption();
        return total != null ? total : 0.0;
    }
}