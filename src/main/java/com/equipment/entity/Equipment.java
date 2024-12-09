package com.equipment.entity;

import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipmentId;
    private String equipmentName;
    private String equipmentDescription;
    private String equipmentCondition;
    private String firstServiceDate;
    private String notes;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne //removed cascade all. deleting equipment was also deleting all equipment associated with job. 
    @JoinColumn(name = "job_id")
    private Job job;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "employee_equipment",
               joinColumns = @JoinColumn(name = "employee_id"),
               inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Set<Employee> employee; 
    
      
}
