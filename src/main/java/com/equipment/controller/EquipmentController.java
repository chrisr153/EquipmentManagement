package com.equipment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.equipment.controller.model.JobEmployee;
import com.equipment.controller.model.JobEquipment;
import com.equipment.entity.Employee;
import com.equipment.controller.model.JobData;
import com.equipment.service.EquipmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/equipment")
@Slf4j
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    // JOB CRUD OPERATIONS

    @PostMapping("/job")
    @ResponseStatus(code = HttpStatus.CREATED)
    public JobData insertJob(@RequestBody JobData equipmentJob) {
        log.info("Creating job record {}", equipmentJob);
        return equipmentService.saveEquipmentJob(equipmentJob);
    }

    @PutMapping("/job/{jobId}")
    public JobData updateJob(@PathVariable Long jobId, @RequestBody JobData equipmentJob) {
        equipmentJob.setJobId(jobId);
        log.info("Updating job info {}", equipmentJob);
        return equipmentService.saveEquipmentJob(equipmentJob);
    }

    @GetMapping("/job")
    public List<JobData> retrieveAllJobs() {
        log.info("Retrieving all Jobs Info");
        return equipmentService.retrieveAllJobs();
    }

    @GetMapping("/job/{jobId}")
    public JobData retrieveJobById(@PathVariable Long jobId) {
        log.info("Retrieving Job info with ID={}", jobId);
        return equipmentService.retrieveJobById(jobId);
    }
    
    // EQUIPMENT 
    @PostMapping("/{jobId}/equipment")
    @ResponseStatus(code = HttpStatus.CREATED)
    public JobEquipment addEquipmenToJob(@PathVariable Long jobId, @RequestBody JobEquipment jobEquipment) {
        log.info("Creating equipment record for job with ID={}", jobId);
       return equipmentService.saveJobEquipment(jobEquipment);
    }
    
    //EMPLOYEE
    @PostMapping("/{jobId}/employee")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Employee addEmployeeToJob(@PathVariable Long jobId, @RequestBody JobEmployee jobEmployee) {
    	log.info("Creating Employee record for job with ID={}", jobId);
    	jobEmployee.setJobId(jobId);
        return equipmentService.saveJobEmployee(jobEmployee);
    }
    @PutMapping("/equipment/{equipmentId}")
    public JobEquipment updateJobEquipment(@PathVariable Long equipmentId, @RequestBody JobEquipment jobEquipment) {
        jobEquipment.setEquipmentId(equipmentId);
        log.info("Updating equipment information {}", jobEquipment);
        return equipmentService.saveJobEquipment(jobEquipment);
    }

    @GetMapping("/equipment")
    public List<JobEquipment> retrieveAllEquipment() {
        log.info("Retrieving all Equipment Info");
        return equipmentService.retrieveAllEquipment();
    }

    @GetMapping("/equipment/{equipmentId}")
    public JobEquipment retrieveEquipmentById(@PathVariable Long equipmentId) {
        log.info("Retrieving equipment info with ID={}", equipmentId);
        return equipmentService.retrieveEquipmentById(equipmentId);
    }

    @DeleteMapping("/{equipmentId}")
    public ResponseEntity<Map<String,String>> deleteEquipmentById1(@PathVariable Long equipmentId) {
        log.info("Deleting equipment info with ID={}", equipmentId);
        equipmentService.deleteEquipmentById(equipmentId);
        Map<String, String> message = Map.of("message", "Equipment with ID=" + equipmentId + " was deleted.");
        return ResponseEntity.ok(message);
    
    }

    // EMPLOYEE CRUD OPERATIONS
    @PutMapping("/employee/{employeeId}")
    public Employee updateEmployee(@PathVariable Long employeeId, @RequestBody JobEmployee employee) {
        employee.setEmployeeId(employeeId);
        log.info("Updating employee info {}", employee);
        return equipmentService.updateJobEmployee(employeeId, employee);
    }

    @GetMapping("/employee/{employeeId}")
    public Employee retrieveEmployeeById(@PathVariable Long employeeId) {
        log.info("Retrieving employee info with ID={}", employeeId);
        return equipmentService.retrieveEmployeeById(employeeId);
    }
    
    @GetMapping("/employee")
    public List<JobEmployee> retrieveAllEmployees() {
        log.info("Retrieving all employees");
        return equipmentService.retrieveAllEmployees();
        
    }

    @DeleteMapping("/employee/{employeeId}")
    public Map<String, String> deleteEmployeeById(@PathVariable Long employeeId) {
       log.info("Deleting employee info with ID={}", employeeId);
      equipmentService.deleteEmployeeById(employeeId);
       return Map.of("message", "Employee with ID=" + employeeId + " was deleted.");
    }

    
}
