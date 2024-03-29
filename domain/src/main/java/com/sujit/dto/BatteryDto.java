package com.sujit.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BatteryDto implements Serializable {

  private static final long serialVersionUID = -12456875L;

  private String batteryId;

  private String name;

  private Integer postCode;

  private Double wattCapacity;

  private LocalDateTime createdDate;

  private LocalDateTime lastModifiedDate;
}
