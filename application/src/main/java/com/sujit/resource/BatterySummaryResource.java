package com.sujit.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatterySummaryResource {

  private String batteryId;

  private String name;

  private Integer postCode;

  private Double wattCapacity;

  private LocalDateTime createdDate;

  private LocalDateTime lastModifiedDate;
}
