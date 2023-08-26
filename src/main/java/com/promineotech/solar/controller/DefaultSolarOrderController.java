package com.promineotech.solar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.solar.entity.OrderRequest;
import com.promineotech.solar.entity.Project;
import com.promineotech.solar.service.SolarOrderService;

@RestController
public class DefaultSolarOrderController implements SolarOrderController {

  @Autowired
  private SolarOrderService jeepOrderService;
  
  @Override
  public Project createOrder(OrderRequest orderRequest) {
    return jeepOrderService.createProject(orderRequest);
  }

}
