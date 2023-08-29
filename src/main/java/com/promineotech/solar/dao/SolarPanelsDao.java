package com.promineotech.solar.dao;

import java.util.List;
import com.promineotech.solar.entity.Panel;
import com.promineotech.solar.entity.PanelManufacturer;

public interface SolarPanelsDao {

  /**
   * 
   * @param panelManufacturer
   * @return List<Panel>
   */
  List<Panel> fetchPanels(PanelManufacturer panelManufacturer);

}
