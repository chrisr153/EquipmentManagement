package com.equipment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.equipment.entity.Job;

@Repository
public interface JobDao extends JpaRepository<Job, Long> {

}
