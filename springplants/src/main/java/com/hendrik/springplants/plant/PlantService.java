package com.hendrik.springplants.plant;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PlantService {

    private final PlantRepository plantRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> getPlants() {
	    return plantRepository.findAll();
	}
    
    public void addNewPlant(Plant plant) {
        Optional<Plant> plantOptional =  plantRepository.findPlantByPlantName(plant.getName());
        if (plantOptional.isPresent()) {
            throw new IllegalStateException("Name is already taken");
        }
        plantRepository.save(plant);
    }

    @Transactional
    public void updatePlant(Long plantId, String name, String type) {
        Plant plant = plantRepository.findById(plantId).orElseThrow(() -> new IllegalStateException("plant with id " + plantId + " does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(plant.getName(), name)) {
            plant.setName(name);
        }

        if(type != null && type.length() > 0 && !Objects.equals(plant.getType(), type)) {
            plant.setType(type);
        }
    }
}
