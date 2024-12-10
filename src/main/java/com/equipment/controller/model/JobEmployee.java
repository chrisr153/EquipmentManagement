package com.equipment.controller.model;


import com.equipment.entity.Employee;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobEmployee {
	
			@Id
			private Long employeeId;
			private String employeeFirstName;
			private String employeeLastName;
			private String employeeJobTitle;
			private String employeePhone;
			private String employeeEmail;
			private Long jobId;
			
			public JobEmployee (JobEmployee jobEmployee) {
				
				employeeId = jobEmployee.getEmployeeId();
				employeeFirstName = jobEmployee.getEmployeeFirstName();
				employeeLastName = jobEmployee.getEmployeeLastName();
				employeeJobTitle = jobEmployee.getEmployeeJobTitle();
				employeePhone = jobEmployee.getEmployeePhone();
				employeeEmail = jobEmployee.getEmployeeEmail();
				jobId=jobEmployee.getJobId();
				
			}
				
			public JobEmployee (Employee employee) {
					
				employeeId = employee.getEmployeeId();
				employeeFirstName = employee.getEmployeeFirstName();
				employeeLastName = employee.getEmployeeLastName();
				employeeJobTitle = employee.getEmployeeJobTitle();
				employeePhone = employee.getEmployeePhone();
				employeeEmail = employee.getEmployeeEmail();
				if(employee.getJob() != null) {
					jobId= employee.getJob().getJobId();
				}
					
			}
			

			
		

}
