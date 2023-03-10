package com.sujit.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

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
