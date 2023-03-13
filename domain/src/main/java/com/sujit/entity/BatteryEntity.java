package com.sujit.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class BatteryEntity implements Serializable {

  private static final long serialVersionUID = -95456875L;

  @Id
  private String batteryId;

  private String name;

  private Integer postCode;

  private Double wattCapacity;

  private LocalDateTime createdDate;

  private LocalDateTime lastModifiedDate;
}
