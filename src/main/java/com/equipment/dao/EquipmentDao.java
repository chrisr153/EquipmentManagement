package com.equipment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.equipment.entity.Equipment;

@Repository
public interface EquipmentDao extends JpaRepository<Equipment, Long> {

}
