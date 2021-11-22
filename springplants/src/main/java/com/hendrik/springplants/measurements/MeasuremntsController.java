package com.hendrik.springplants.measurements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;;

@RestController
@RequestMapping(path = "/api/measurements")
@CrossOrigin
public class MeasuremntsController {
    private final MeasurementsService measurementsService;

    @Autowired
    public MeasuremntsController(MeasurementsService measurementsService) {
        this.measurementsService = measurementsService;
    }

    @GetMapping
    public List<Measurements> getMeasurements() {
        return measurementsService.getMeasurements();
    }

    @GetMapping(value = "temp/" + "{plantId}")
    public List<Integer> getTempByPlantId(@PathVariable("plantId") Long plantId) {
        return measurementsService.getTempByPlantId(plantId);
    }

    @GetMapping(value = "humidity/" + "{plantId}")
    public List<Integer> getHumidityByPlantId(@PathVariable("plantId") Long plantId) {
        return measurementsService.getHumidityByPlantId(plantId);
    }

    @GetMapping(value = "moisture/" + "{plantId}")
    public List<Integer> getMoistureByPlantId(@PathVariable("plantId") Long plantId) {
        return measurementsService.getMoistureByPlantId(plantId);
    }

    @GetMapping(value = "light/" + "{plantId}")
    public List<Integer> getLightByPlantId(@PathVariable("plantId") Long plantId) {
        return measurementsService.getLightByPlantId(plantId);
    }

    @PostMapping
    public void addMeasurements(@RequestBody Measurements measurements) {
        measurementsService.addNewMeasurements(measurements);
    }
}
