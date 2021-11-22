package com.hendrik.springplants.plant;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "plant")
public class Plant {
    @Id
    @SequenceGenerator(
        name = "plant_sequence",
        sequenceName = "plant_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "plant_sequence"
    )
    private Long plantId;
    private String plantName;
    private String plantType;
    @CreationTimestamp
    private LocalDate dop;

    public Plant() {
    }

    public Plant(Long plantId,
                 String plantName,
                 String plantType,
                 LocalDate dop) {
        this.plantId = plantId;
        this.plantName = plantName;
        this.plantType = plantType;
        this.dop = dop;
    }

    public Plant(String plantName,
                 String plantType,
                 LocalDate dop) {
        this.plantName = plantName;
        this.plantType = plantType;
        this.dop = dop;
    }

    public Long getId(){
        return plantId;
    }
    public void setID(Long plantId){
        this.plantId = plantId;
    }

    public String getName(){
        return plantName;
    }
    public void setName(String plantName){
        this.plantName = plantName;
    }

    public String getType(){
        return plantType;
    }
    public void setType(String plantType){
        this.plantType = plantType;
    }

    public LocalDate getDop(){
        return dop;
    }
    public void setDop(LocalDate dop){
        this.dop = dop;
    }

    @Override
    public String toString() {
        return "Plant{" +
               "plantId= " + plantId +
               ", plantName= " + plantName +
               ", plantType= " + plantType +
               ", dop= " + dop +
               "}";
    }
}

