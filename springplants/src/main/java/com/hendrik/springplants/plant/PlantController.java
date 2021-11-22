package com.hendrik.springplants.plant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/plant")
@CrossOrigin()
public class PlantController {

    private final PlantService plantService;

    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping
    public List<Plant> getPlants() {
        return plantService.getPlants();
    }

    @PostMapping
    public void registerNewPlant(@RequestBody Plant plant) {
        plantService.addNewPlant(plant);
    }

    @PutMapping(path = "{plantId}")
    public void updatePlant(@PathVariable("plantId") Long plantId, @RequestParam(required = false) String name,
            @RequestParam(required = false) String type) {
        plantService.updatePlant(plantId, name, type);
    }

}
