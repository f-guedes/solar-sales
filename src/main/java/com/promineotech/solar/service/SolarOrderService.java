package com.promineotech.solar.service;

import com.promineotech.solar.entity.Project;
import com.promineotech.solar.entity.OrderRequest;

public interface SolarOrderService {

  Project createProject(OrderRequest orderRequest);
}
