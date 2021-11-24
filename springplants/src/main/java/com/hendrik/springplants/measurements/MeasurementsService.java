package com.hendrik.springplants.measurements;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<Integer> getTempBetween(Long id, Date begin, Date end) {
        return measurementsRepository.findTempByPlantIdBetween(id, begin, end);
    }

    public List<Integer> getHumidityBetween(Long id, Date begin, Date end) {
        return measurementsRepository.findHumidityByPlantIdBetween(id, begin, end);
    }

    public List<Integer> getMoistureBetween(Long id, Date begin, Date end) {
        return measurementsRepository.findMoistureByPlantIdBetween(id, begin, end);
    }

    public List<Integer> getLightBetween(Long id, Date begin, Date end) {
        return measurementsRepository.findLightByPlantIdBetween(id, begin, end);
    }

    public Page<Integer> getTempWithPaginationAndSorting(int offset, int pageSize, String field, Long id) {
        Page<Integer> temps = measurementsRepository.findTempByPlantId(id, PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return temps;
    }

    public Page<Integer> getHumidityWithPaginationAndSorting(int offset, int pageSize, String field, Long id) {
        Page<Integer> hums = measurementsRepository.findHumidityByPlantId(id, PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return hums;
    }

    public Page<Integer> getMoistureWithPaginationAndSorting(int offset, int pageSize, String field, Long id) {
        Page<Integer> mois = measurementsRepository.findMoistureByPlantId(id, PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return mois;
    }

    public Page<Integer> getLightWithPaginationAndSorting(int offset, int pageSize, String field, Long id) {
        Page<Integer> lights = measurementsRepository.findLightByPlantId(id, PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return lights;
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
