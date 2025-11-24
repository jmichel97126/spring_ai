package com.example.springai_smart_home_controller.controller;


import com.example.springai_smart_home_controller.model.Device;
import com.example.springai_smart_home_controller.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/devices")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class DeviceController {
    
    @Autowired
    private DeviceService deviceService;
    
    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }
    
    @GetMapping("/room/{room}")
    public ResponseEntity<List<Device>> getDevicesByRoom(@PathVariable String room) {
        return ResponseEntity.ok(deviceService.getDevicesByRoom(room));
    }
    
    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        try {
            Device savedDevice = deviceService.saveDevice(device);
            return ResponseEntity.ok(savedDevice);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/{name}/toggle")
    public ResponseEntity<Map<String, Object>> toggleDevice(@PathVariable String name) {
        try {
            Device device = deviceService.toggleDevice(name);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "device", device,
                "message", "Device toggled successfully"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "error", e.getMessage()
            ));
        }
    }
}