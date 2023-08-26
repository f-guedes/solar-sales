package com.promineotech.solar.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Inverter {
  private Long inverterPK;
  private InverterManufacturer inverterManufacturer;
}
