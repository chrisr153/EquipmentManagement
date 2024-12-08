package com.equipment.controller.model;


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
			
			public JobEmployee (JobEmployee savedJobEmployee) {
				
				employeeId = savedJobEmployee.getEmployeeId();
				employeeFirstName = savedJobEmployee.getEmployeeFirstName();
				employeeLastName = savedJobEmployee.getEmployeeLastName();
				employeeJobTitle = savedJobEmployee.getEmployeeJobTitle();
				employeePhone = savedJobEmployee.getEmployeePhone();
				employeeEmail = savedJobEmployee.getEmployeeEmail();
				
			}


}
