package com.hendrik.springplants.measurements;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping(value = "temp/{plantId}/{begin}/{end}")
    public List<Integer> getTempByPlantIdAndTimePeriod(@PathVariable("plantId") Long id, @PathVariable("begin") Date begin, @PathVariable("end") Date end) {
        return measurementsService.getTempByTimePeriod(id, begin, end);
    }

    @GetMapping(value = "temp/{plantId}/pagination/{field}/{offset}/{pageSize}")
    public Page<Integer> getTempByPlantIdWithPaginationAndSorting(@PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize, @PathVariable("field") String field, @PathVariable("plantId") Long id) {
        Page<Integer> tempsWithPagination = measurementsService.getTempWithPaginationAndSorting(offset, pageSize, field, id);
        return tempsWithPagination;
    }

    @GetMapping(value = "humidity/{plantId}/pagination/{field}/{offset}/{pageSize}")
    public Page<Integer> getHumidityByPlantIdWithPaginationAndSorting(@PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize, @PathVariable("field") String field, @PathVariable("plantId") Long id) {
        Page<Integer> humsWithPagination = measurementsService.getHumidityWithPaginationAndSorting(offset, pageSize, field, id);
        return humsWithPagination;
    }

    @GetMapping(value = "moisture/{plantId}/pagination/{field}/{offset}/{pageSize}")
    public Page<Integer> getMoistureByPlantIdWithPaginationAndSorting(@PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize, @PathVariable("field") String field, @PathVariable("plantId") Long id) {
        Page<Integer> moisWithPagination = measurementsService.getMoistureWithPaginationAndSorting(offset, pageSize, field, id);
        return moisWithPagination;
    }

    @GetMapping(value = "light/{plantId}/pagination/{field}/{offset}/{pageSize}")
    public Page<Integer> getLightByPlantIdWithPaginationAndSorting(@PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize, @PathVariable("field") String field, @PathVariable("plantId") Long id) {
        Page<Integer> lightsWithPagination = measurementsService.getLightWithPaginationAndSorting(offset, pageSize, field, id);
        return lightsWithPagination;
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
