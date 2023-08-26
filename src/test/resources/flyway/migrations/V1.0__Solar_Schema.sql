DROP TABLE IF EXISTS customer_projects;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS inverters;
DROP TABLE IF EXISTS panels;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
  customer_pk int unsigned NOT NULL AUTO_INCREMENT,
  customer_id varchar(40) NOT NULL,
  first_name varchar(45) NOT NULL, 
  last_name varchar(45) NOT NULL,
  phone varchar(20),
  address varchar (70),
  PRIMARY KEY (customer_pk),
  UNIQUE KEY (customer_id)
);

CREATE TABLE panels (
  panel_pk int unsigned NOT NULL AUTO_INCREMENT,
  panel_manufacturer enum('CANADIAN_SOLAR', 'FREEDOM_FOREVER', 'HYUNDAI', 'JINKO', 'LG', 'LONGI', 'MISSION_SOLAR', 'OTHER', 'QCELLS', 'REC', 'SILFAB', 'TESLA') NOT NULL,
  model varchar(40),
  wattage int NOT NULL,
  is_triple_black BOOLEAN NOT NULL,
  PRIMARY KEY (panel_pk),
  UNIQUE KEY (panel_manufacturer, model, wattage)
);

CREATE TABLE inverters (
  inverter_pk int unsigned NOT NULL AUTO_INCREMENT,
  inverter_manufacturer enum('SOLAREDGE', 'ENPHASE'),
  PRIMARY KEY (inverter_pk),
  UNIQUE KEY (inverter_manufacturer)
);


CREATE TABLE projects (
  project_pk int unsigned NOT NULL AUTO_INCREMENT,
  system_size decimal (5,3) NOT NULL,
  panel_fk int unsigned NOT NULL,
  inverter_fk int unsigned NOT NULL,
  gross_price decimal(9, 2) NOT NULL,
  PRIMARY KEY (project_pk),
  FOREIGN KEY (panel_fk) REFERENCES panels (panel_pk) ON DELETE CASCADE,
  FOREIGN KEY (inverter_fk) REFERENCES inverters (inverter_pk) ON DELETE CASCADE
);

CREATE TABLE customer_projects (
  customer_fk int unsigned NOT NULL,
  project_fk int unsigned NOT NULL,
  FOREIGN KEY (customer_fk) REFERENCES customers (customer_pk) ON DELETE CASCADE,
  FOREIGN KEY (project_fk) REFERENCES projects (project_pk) ON DELETE CASCADE
);