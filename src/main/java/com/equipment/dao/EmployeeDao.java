package com.equipment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.equipment.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {

	

}
