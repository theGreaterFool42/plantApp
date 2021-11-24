package com.hendrik.springplants.measurements;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementsRepository extends  JpaRepository<Measurements, Long>{

    @Query("SELECT m.temp FROM Measurements m WHERE m.plantId = ?1")
    Page<Integer> findTempByPlantId(Long id, Pageable pageable);

    @Query("SELECT m.humidity FROM Measurements m WHERE m.plantId = ?1")
    Page<Integer> findHumidityByPlantId(Long id, Pageable pageable);

    @Query("SELECT m.moisture FROM Measurements m WHERE m.plantId = ?1")
    Page<Integer> findMoistureByPlantId(Long id, Pageable pageable);

    @Query("SELECT m.light FROM Measurements m WHERE m.plantId = ?1")
    Page<Integer> findLightByPlantId(Long id, Pageable pageable);

    @Query("SELECT m.temp FROM Measurements m WHERE m.plantId = ?1 ORDER BY timeStamp ")
    List<Integer> findTempByPlantId(Long id);

    @Query("SELECT m.humidity FROM Measurements m WHERE m.plantId = ?1 ORDER BY timeStamp")
    List<Integer> findHumidityByPlantId(Long id);

    @Query("SELECT m.moisture FROM Measurements m WHERE m.plantId = ?1 ORDER BY timeStamp")
    List<Integer> findMoistureByPlantId(Long id);

    @Query("SELECT m.light FROM Measurements m WHERE m.plantId = ?1 ORDER BY timeStamp")
    List<Integer> findLightByPlantId(Long id);
}
