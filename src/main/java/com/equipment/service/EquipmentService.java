package com.equipment.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipment.controller.model.JobEmployee;
import com.equipment.controller.model.JobEquipment;
import com.equipment.controller.model.JobData;
import com.equipment.dao.EmployeeDao;
import com.equipment.dao.EquipmentDao;
import com.equipment.dao.JobDao;
import com.equipment.entity.Employee;
import com.equipment.entity.Equipment;
import com.equipment.entity.Job;

import jakarta.transaction.Transactional;

@Service
public class EquipmentService {


    @Autowired
    private EquipmentDao equipmentDao;
    
    @Autowired
    private EmployeeDao employeeDao;
    
    @Autowired
    private JobDao jobDao;

    // Grouped Methods by function for easier readability. 
    
    // Equipment Methods-retrieving, updating, and deleting.
    public List<JobEquipment> retrieveAllEquipment() {
        List<Equipment> equipments = equipmentDao.findAll();
        List<JobEquipment> result = new LinkedList<>();
        for (Equipment equipment : equipments) {
            JobEquipment eqd = new JobEquipment(equipment);
            result.add(eqd);
        }
        return result;
    }

    public JobEquipment retrieveEquipmentById(Long equipmentId) {
        return new JobEquipment(findEquipmentById(equipmentId));
    }

    @Transactional
    public JobEquipment saveJobEquipment(JobEquipment jobEquipment) {
        Long equipmentId = jobEquipment.getEquipmentId();
        Equipment equipment = findOrCreateEquipment(equipmentId);
        copyEquipmentFields(equipment, jobEquipment);

        Job job = findJobById(jobEquipment.getJobId());
        equipment.setJob(job);

        equipment = equipmentDao.save(equipment);
        return new JobEquipment(equipment);
    }

    public void deleteEquipmentById(Long equipmentId) {
        Equipment equipment = equipmentDao.findById(equipmentId)
            .orElseThrow(() -> new NoSuchElementException("Equipment with ID=" + equipmentId + " not found."));
        equipmentDao.delete(equipment);
    }

    // Employee Methods- retrieving, updating, and deleting.
    public List<JobEmployee> retrieveAllEmployees() {
        List<Employee> employees = employeeDao.findAll();
        List<JobEmployee> result = new LinkedList<>();
        for (Employee employee : employees) {
            JobEmployee jobEmployee = new JobEmployee();
            copyEmployeeFields(employee, jobEmployee);
            result.add(jobEmployee);
        }
        return result;
    }

    public Employee retrieveEmployeeById(Long employeeId) {
        return findEmployeeById(employeeId);
    }

    @Transactional
    public Employee updateJobEmployee(Long employeeId, JobEmployee jobEmployee) {
        Employee employee = employeeDao.findById(employeeId).orElseThrow(()-> new NoSuchElementException("Employee with ID=" + employeeId + " not found."));
        employee.setEmployeeFirstName(jobEmployee.getEmployeeFirstName());
        employee.setEmployeeLastName(jobEmployee.getEmployeeLastName());
        employee.setEmployeeJobTitle(jobEmployee.getEmployeeJobTitle());
        employee.setEmployeePhone(jobEmployee.getEmployeePhone());
        employee.setEmployeeEmail(jobEmployee.getEmployeeEmail());
        
        Employee updatedEmployee= employeeDao.save(employee);
        
        return updatedEmployee;
    }

    
    @Transactional
    public void deleteEmployeeById(Long employeeId) {
        Employee employee = findEmployeeById(employeeId);
        if (Objects.isNull(employee)) {
            throw new NoSuchElementException("Employee with ID=" + employeeId + " not found.");
        }
        employeeDao.delete(employee);
    }
    @Transactional 
    public Employee saveJobEmployee(JobEmployee jobEmployee) { 
       	Long jobId = jobEmployee.getJobId();
    	Long employeeId = jobEmployee.getEmployeeId(); 
    	Employee employee = findOrCreateEmployee(employeeId); 
    	copyEmployeeFieldsPost(employee, jobEmployee); 
    	Job job = findJobById(jobId);
    	employee.setJob(job);
    	job.getEmployee().add(employee);
    	employee = employeeDao.save(employee); 
    	
    	return employee;
    }

   
    // Job Methods- retrieving, saving , and updating.
    public List<JobData> retrieveAllJobs() {
        List<Job> jobs = jobDao.findAll();
        List<JobData> jobResult = new LinkedList<>();
        for (Job job : jobs) {
            JobData eqj = new JobData(job);
            jobResult.add(eqj);
        }
        return jobResult;
    }

    @Transactional
    public JobData retrieveJobById(Long jobId) {
        return new JobData(findJobById(jobId));
    }

    @Transactional
    public JobData saveEquipmentJob(JobData jobData) {
        Long jobId = jobData.getJobId();
        Job job = findOrCreateJob(jobId);
        copyJobFields(job, jobData);
        jobDao.save(job);
        return new JobData(job);
        
    }


    //Helper Methods perform copying and finding entities. 
    private Equipment findOrCreateEquipment(Long equipmentId) {
        if (Objects.isNull(equipmentId)) {
            return new Equipment();
        } else {
            return equipmentDao.findById(equipmentId)
                .orElseThrow(() -> new NoSuchElementException("Equipment with ID=" + equipmentId + " not found."));
        }
    }

    private void copyEquipmentFields(Equipment equipment, JobEquipment jobEquipment) {
        equipment.setEquipmentName(jobEquipment.getEquipmentName());
        equipment.setEquipmentDescription(jobEquipment.getEquipmentDescription());
        equipment.setEquipmentCondition(jobEquipment.getEquipmentCondition());
        equipment.setFirstServiceDate(jobEquipment.getFirstServiceDate());
        equipment.setNotes(jobEquipment.getNotes());
    }

    private Equipment findEquipmentById(Long equipmentId) {
        return equipmentDao.findById(equipmentId)
            .orElseThrow(() -> new NoSuchElementException("Equipment with ID=" + equipmentId + " doesn't exist."));
    }

    private Employee findOrCreateEmployee(Long employeeId) {
        if (Objects.isNull(employeeId)) {
            return new Employee();
        }
        return findEmployeeById(employeeId);
    }

    //This method is used for GET request.
    private void copyEmployeeFields(Employee employee, JobEmployee jobEmployee) {
    	jobEmployee.setEmployeeId(employee.getEmployeeId()); 
    	jobEmployee.setEmployeeFirstName(employee.getEmployeeFirstName()); 
    	jobEmployee.setEmployeeLastName(employee.getEmployeeLastName()); 
    	jobEmployee.setEmployeeJobTitle(employee.getEmployeeJobTitle()); 
    	jobEmployee.setEmployeePhone(employee.getEmployeePhone()); 
    	jobEmployee.setEmployeeEmail(employee.getEmployeeEmail());
    	jobEmployee.setJobId(employee.getJob().getJobId());

    	
    }
    
    //This method is used for POST request. 
    private void copyEmployeeFieldsPost(Employee employee, JobEmployee jobEmployee) {
    	employee.setEmployeeId(jobEmployee.getEmployeeId()); 
    	employee.setEmployeeFirstName(jobEmployee.getEmployeeFirstName()); 
    	employee.setEmployeeLastName(jobEmployee.getEmployeeLastName()); 
    	employee.setEmployeeJobTitle(jobEmployee.getEmployeeJobTitle()); 
    	employee.setEmployeePhone(jobEmployee.getEmployeePhone()); 
    	employee.setEmployeeEmail(jobEmployee.getEmployeeEmail());

    	
    }

    private Employee findEmployeeById(Long employeeId) {
        return employeeDao.findById(employeeId)
            .orElseThrow(() -> new NoSuchElementException("Employee with ID=" + employeeId + " not found."));
    }

    private Job findOrCreateJob(Long jobId) {
        if (Objects.isNull(jobId)) {
            return new Job();
        } else {
            return jobDao.findById(jobId)
                .orElseThrow(() -> new NoSuchElementException("Job with ID=" + jobId + " not found."));
        }
    }

    private void copyJobFields(Job job, JobData jobData) {
        job.setJobName(jobData.getJobName());
        job.setJobAddress(jobData.getJobAddress());
        job.setJobCity(jobData.getJobCity());
        job.setJobState(jobData.getJobState());
        job.setJobZip(jobData.getJobZip());
        job.setJobDescription(jobData.getJobDescription());
        job.setJobStartDate(jobData.getJobStartDate());
        job.setJobEndDate(jobData.getJobEndDate());
    }

    private Job findJobById(Long jobId) {
        return jobDao.findById(jobId)
            .orElseThrow(() -> new NoSuchElementException("Job with ID=" + jobId + " not found."));
    }
}
