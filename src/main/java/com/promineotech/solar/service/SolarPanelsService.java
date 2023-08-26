package com.promineotech.solar.service;

import java.util.List;
import com.promineotech.solar.entity.Panel;
import com.promineotech.solar.entity.PanelManufacturer;

public interface SolarPanelsService {

  List<Panel> fetchPanels(PanelManufacturer panelManufacturer);

}
