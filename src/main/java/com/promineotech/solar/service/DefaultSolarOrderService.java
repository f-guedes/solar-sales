package com.promineotech.solar.service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.solar.dao.SolarOrderDao;
import com.promineotech.solar.entity.Customer;
import com.promineotech.solar.entity.Panel;
import com.promineotech.solar.entity.Project;
import com.promineotech.solar.entity.OrderRequest;
import com.promineotech.solar.entity.Inverter;

@Service
public class DefaultSolarOrderService implements SolarOrderService {
  
  @Autowired
  private SolarOrderDao solarOrderDao;

  @Transactional
  @Override
  public Project createProject(OrderRequest orderRequest) {
    
    Customer customer = getCustomer(orderRequest);
    Float systemSize = orderRequest.getSystemSize();
    Panel panel = getPanel(orderRequest);    
    Inverter inverter = getInverter(orderRequest);
    BigDecimal grossPrice = orderRequest.getGrossPrice();
    
    return solarOrderDao.saveProject(customer, systemSize, panel, inverter, grossPrice);
  }
  
  /**
   * 
   * @param orderRequest
   * @return
   */
  private Customer getCustomer(OrderRequest orderRequest) {
    return solarOrderDao.fetchCustomer(orderRequest.getCustomer())
        .orElseThrow(() -> new NoSuchElementException("Customer with ID "
            + orderRequest.getCustomer() + " was not found"));
  }

  /**
   * 
   * @param orderRequest
   * @return
   */
  private Panel getPanel(OrderRequest orderRequest) {
    return solarOrderDao
        .fetchPanel(orderRequest.getPanelManufacturer(), orderRequest.getPanelWattage())
        .orElseThrow(() -> new NoSuchElementException("Panel from manufacturer "
            + orderRequest.getPanelManufacturer() + " was not found"));
  }


  /**
   * 
   * @param orderRequest
   * @return
   */
  private Inverter getInverter(OrderRequest orderRequest) {
    return solarOrderDao.fetchInverter(orderRequest.getInverter())
        .orElseThrow(() -> new NoSuchElementException(
            "Inverter " + orderRequest.getInverter() + " was not found"));
  }

}
