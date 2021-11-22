package com.hendrik.springplants.measurements;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "measurements")
public class Measurements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measurement_id")
    private Long id;
    @Column(name = "plant_id")
    private Long plantId;
    @Column(name = "time_stamp")
    @CreationTimestamp
    private LocalDateTime timeStamp;
    private int temp;
    private int humidity;
    private int moisture;
    private int light;

    public Measurements() {

    }

    public Measurements(Long id, Long plantId, LocalDateTime timeStamp, int temp, int humidity, int moisture,
            int light) {
        this.id = id;
        this.plantId = plantId;
        this.timeStamp = timeStamp;
        this.temp = temp;
        this.humidity = humidity;
        this.moisture = moisture;
        this.light = light;
    }

    public Measurements(Long plantId, LocalDateTime timeStamp, int temp, int humidity, int moisture, int light) {
        this.plantId = plantId;
        this.timeStamp = timeStamp;
        this.temp = temp;
        this.humidity = humidity;
        this.moisture = moisture;
        this.light = light;
    }

    public Measurements(LocalDateTime timeStamp, int temp) {
        this.timeStamp = timeStamp;
        this.temp = temp;
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public LocalDateTime gettimeStamp() {
        return timeStamp;
    }

    public void settimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getMoisture() {
        return moisture;
    }

    public void setMoisture(int moisture) {
        this.moisture = moisture;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    
}
