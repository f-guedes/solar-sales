package com.promineotech.solar.dao;

import java.math.BigDecimal;
import java.util.Optional;
import com.promineotech.solar.entity.Customer;
import com.promineotech.solar.entity.Panel;
import com.promineotech.solar.entity.PanelManufacturer;
import com.promineotech.solar.entity.Project;
import com.promineotech.solar.entity.Inverter;

public interface SolarOrderDao {

  Optional<Customer> fetchCustomer(String customerId);

  Optional<Panel> fetchPanel(PanelManufacturer panelManufacturer, int wattage);

  Optional<Inverter> fetchInverter(String inverterManufacturer);

  Project saveProject(Customer customer, Float systemSize, Panel panel,
      Inverter inverter, BigDecimal grossPrice);
}
