package com.promineotech.solar.entity;

import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class OrderRequest {
  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[\\w\\s]*")
  private String customer;
  
  @Positive
  @Min(2)
  @Max(99)
  private Float systemSize;
  
  @NotNull
  private PanelManufacturer panelManufacturer;
  
  @NotNull
  private int panelWattage;
  
  @NotNull
  private String inverter;
  
  @NotNull
  private BigDecimal grossPrice;
}
