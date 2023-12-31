package com.promineotech.solar.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Panel {
  private Long panelPK;
  private PanelManufacturer panelManufacturer;
  private String model;
  private int wattage;
  private Boolean isTripleBlack;

  
}

