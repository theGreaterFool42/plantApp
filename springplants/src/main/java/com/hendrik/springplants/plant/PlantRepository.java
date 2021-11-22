package com.hendrik.springplants.plant;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long>{
    @Query("SELECT p FROM Plant p WHERE p.plantName= ?1")
    Optional<Plant> findPlantByPlantName(String name);
}
