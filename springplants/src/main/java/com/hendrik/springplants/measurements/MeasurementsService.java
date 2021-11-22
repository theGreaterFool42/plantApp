package com.hendrik.springplants.measurements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeasurementsService {
    
    private final MeasurementsRepository measurementsRepository;
    
    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }
    public List<Measurements> getMeasurements() {
        return measurementsRepository.findAll();
    }
    public List<Integer> getTempByPlantId(Long id) {
        return measurementsRepository.findTempByPlantId(id);
    }
    public List<Integer> getHumidityByPlantId(Long id) {
        return measurementsRepository.findHumidityByPlantId(id);
    }
    public List<Integer> getMoistureByPlantId(Long id) {
        return measurementsRepository.findMoistureByPlantId(id);
    }
    public List<Integer> getLightByPlantId(Long id) {
        return measurementsRepository.findLightByPlantId(id);
    }

    public void addNewMeasurements(Measurements measurements) {
        measurementsRepository.save(measurements);
    }
}
