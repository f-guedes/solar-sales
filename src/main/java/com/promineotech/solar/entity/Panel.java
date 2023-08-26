package com.promineotech.solar.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Panel {
  private Long panelPK;
  private PanelManufacturer panelManufacturer;
  private String model;
  private int wattage;
  private Boolean isTripleBlack;

  
}

