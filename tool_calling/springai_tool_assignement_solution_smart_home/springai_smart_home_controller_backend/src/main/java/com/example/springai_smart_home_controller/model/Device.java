package com.example.springai_smart_home_controller.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DEVICES")
public class Device {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceType type;
    
    @Column(nullable = false)
    private String room;
    
    @Column(nullable = false)
    private Boolean isOn = false;

    @Column(name = "\"value\"")
    private Double value; // brightness, temperature, etc.
    private String status;
    private LocalDateTime lastUpdated;
    private Double energyConsumption = 0.0;
    
    // Constructors
    public Device() {
        this.lastUpdated = LocalDateTime.now();
    }
    
    public Device(String name, DeviceType type, String room) {
        this();
        this.name = name;
        this.type = type;
        this.room = room;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public DeviceType getType() { return type; }
    public void setType(DeviceType type) { this.type = type; }
    
    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }
    
    public Boolean getIsOn() { return isOn; }
    public void setIsOn(Boolean isOn) { 
        this.isOn = isOn;
        this.lastUpdated = LocalDateTime.now();
    }
    
    public Double getValue() { return value; }
    public void setValue(Double value) { 
        this.value = value;
        this.lastUpdated = LocalDateTime.now();
    }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
    
    public Double getEnergyConsumption() { return energyConsumption; }
    public void setEnergyConsumption(Double energyConsumption) { this.energyConsumption = energyConsumption; }
}