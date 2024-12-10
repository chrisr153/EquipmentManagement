package com.equipment.controller.model;


import com.equipment.entity.Equipment;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class JobEquipment {
		
		@Id
		private Long equipmentId;
		private String equipmentName;
		private String equipmentDescription;
		private String equipmentCondition;
		private String firstServiceDate;
		private String notes;
		private Long jobId;
	
				
		public JobEquipment (Equipment equipment) {
			
			equipmentId = equipment.getEquipmentId();
			equipmentName = equipment.getEquipmentName();
			equipmentDescription = equipment.getEquipmentDescription();
			equipmentCondition = equipment.getEquipmentCondition();
			firstServiceDate = equipment.getFirstServiceDate();
			notes = equipment.getNotes();
			if(equipment.getJob()!= null) {
				jobId= equipment.getJob().getJobId();
			}
			
		}
}


		
