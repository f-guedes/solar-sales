package com.promineotech.solar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.solar.dao.SolarPanelsDao;
import com.promineotech.solar.entity.Panel;
import com.promineotech.solar.entity.PanelManufacturer;

@Service
public class DefaultSolarPanelsService implements SolarPanelsService {
  
  @Autowired
  private SolarPanelsDao solarPanelsDao;

  @Override
  public List<Panel> fetchPanels(PanelManufacturer panelManufacturer) {
    List <Panel> panels = solarPanelsDao.fetchPanels(panelManufacturer);
    return panels;
  }

}
