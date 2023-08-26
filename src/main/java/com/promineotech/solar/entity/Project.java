package com.promineotech.solar.entity;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
  private Long projectPK;
  private Float systemSize;
  private Panel panel;
  private Inverter inverter;
  private BigDecimal grossPrice;
  
//  @JsonIgnore
//  public Long getProjectPK() {
//    return projectPK;
//  }
}
