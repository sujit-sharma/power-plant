package com.sujit.resource;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BatterySummaryResource {

  private String name;

  private Integer postCode;

  private String wattCapacity;
}
