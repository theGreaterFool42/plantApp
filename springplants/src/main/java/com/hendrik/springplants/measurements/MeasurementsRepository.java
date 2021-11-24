package com.hendrik.springplants.measurements;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementsRepository extends  JpaRepository<Measurements, Long>{

    @Query(value = "SELECT m.temp FROM measurements m WHERE m.plant_id = :id AND m.time_stamp BETWEEN :begin AND :end ORDER BY time_stamp", nativeQuery = true)
    List<Integer> findTempByPlantIdBetween(Long id, Date begin, Date end);

    @Query(value = "SELECT m.humidity FROM measurements m WHERE m.plant_id = :id AND m.time_stamp BETWEEN :begin AND :end ORDER BY time_stamp", nativeQuery = true)
    List<Integer> findHumidityByPlantIdBetween(Long id, Date begin, Date end);

    @Query(value = "SELECT m.moisture FROM measurements m WHERE m.plant_id = :id AND m.time_stamp BETWEEN :begin AND :end ORDER BY time_stamp", nativeQuery = true)
    List<Integer> findMoistureByPlantIdBetween(Long id, Date begin, Date end);

    @Query(value = "SELECT m.light FROM measurements m WHERE m.plant_id = :id AND m.time_stamp BETWEEN :begin AND :end ORDER BY time_stamp", nativeQuery = true)
    List<Integer> findLightByPlantIdBetween(Long id, Date begin, Date end);

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
