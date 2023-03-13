package com.sujit.resource;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
public class BatteriesSummaryResource {

  private List<String> batteriesName = new ArrayList<>();

  private Double totalWattCapacity;

  private Double averageWattCapacity;

  public boolean addBattery(Optional<String> battery) {
    return this.batteriesName.add(
        battery.orElseThrow(() -> new NullPointerException("No battery is present to add")));
  }
}
