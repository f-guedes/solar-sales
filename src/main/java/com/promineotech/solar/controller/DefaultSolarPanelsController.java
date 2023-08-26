package com.promineotech.solar.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.solar.entity.Panel;
import com.promineotech.solar.entity.PanelManufacturer;
import com.promineotech.solar.service.SolarPanelsService;

@RestController
public class DefaultSolarPanelsController implements SolarPanelsController {

  @Autowired
  private SolarPanelsService solarPanelsService;
  
  @Override
  public List<Panel> fetchPanels(PanelManufacturer manufacturer) {
    return solarPanelsService.fetchPanels(manufacturer);
  }

}
