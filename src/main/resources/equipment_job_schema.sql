-- Researched solution. gave up going to  comment out. going to manually drop tables from window pane. 
-- Issue resolved. Needed to Drop Join Table First. 

-- DROP JOIN TABLE FIRST
DROP TABLE IF EXISTS employee_equipment;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS equipment;  
DROP TABLE IF EXISTS job;


-- CREATE TABLE THAT CREATE FOREIGN KEYS FIRST TO DROP TABLES. 
CREATE TABLE job (
  job_id INT AUTO_INCREMENT NOT NULL,
  job_name VARCHAR(128) NOT NULL,
  job_address VARCHAR(128) NOT NULL,
  job_city VARCHAR(128) NOT NULL,
  job_state VARCHAR(128) NOT NULL,
  job_zip VARCHAR(5) NOT NULL,
  job_description VARCHAR(258) NOT NULL,
  job_start_date VARCHAR(258) NOT NULL,
  job_end_date VARCHAR(258),
  PRIMARY KEY (job_id)
);
CREATE TABLE equipment (
  equipment_id INT AUTO_INCREMENT NOT NULL,
  job_id INT NOT NULL,
  equipment_name VARCHAR(128) NOT NULL,
  equipment_description VARCHAR(128) NOT NULL,
  equipment_condition VARCHAR(128) NOT NULL,
  first_service_date VARCHAR(128) NOT NULL,
  notes VARCHAR(258) NOT NULL,
  PRIMARY KEY (equipment_id),
  FOREIGN KEY (job_id) REFERENCES job (job_id) ON DELETE CASCADE
);

CREATE TABLE employee (
  employee_id INT AUTO_INCREMENT NOT NULL,
  job_id INT NOT NULL,
  employee_first_name VARCHAR(128) NOT NULL,
  employee_last_name VARCHAR(128) NOT NULL,
  employee_job_title VARCHAR(128) NOT NULL,
  employee_phone VARCHAR(14) NOT NULL,
  employee_email VARCHAR(248) NOT NULL,
  PRIMARY KEY (employee_id),
  FOREIGN KEY (job_id) REFERENCES job (job_id) ON DELETE CASCADE
);
CREATE TABLE employee_equipment (
  employee_id INT NOT NULL,
  equipment_id INT NOT NULL, 
  UNIQUE KEY (employee_id, equipment_id),
  FOREIGN KEY (employee_id) REFERENCES employee (employee_id) ON DELETE CASCADE,
  FOREIGN KEY (equipment_id) REFERENCES equipment (equipment_id) ON DELETE CASCADE
);
