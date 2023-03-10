package com.sujit.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

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
