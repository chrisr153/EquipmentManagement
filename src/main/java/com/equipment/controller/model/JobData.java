package com.equipment.controller.model;

import java.util.HashSet;
import java.util.Set;

import com.equipment.entity.Employee;
import com.equipment.entity.Equipment;
import com.equipment.entity.Job;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobData {

    private Long jobId;
    private String jobName;
    private String jobAddress;
    private String jobCity;
    private String jobState;
    private String jobZip;
    private String jobDescription;
    private String jobStartDate;
    private String jobEndDate;

    private Set<JobEmployee> employees = new HashSet<>();
    private Set<JobEquipment> equipment = new HashSet<>();

    public JobData(Job job) { 

        jobId = job.getJobId();
        jobName = job.getJobName();
        jobAddress = job.getJobAddress();
        jobCity = job.getJobCity();
        jobState = job.getJobState();
        jobZip = job.getJobZip();
        jobDescription = job.getJobDescription();
        jobStartDate = job.getJobStartDate();
        jobEndDate = job.getJobEndDate();

        if (job.getEmployee() != null) {
            for (Employee emp : job.getEmployee()) {
                employees.add(new JobEmployee(emp));
            }
        }

        if (job.getEquipment() != null) {
            for (Equipment eqp : job.getEquipment()) {
                equipment.add(new JobEquipment(eqp));
            }
        }
    }
}
