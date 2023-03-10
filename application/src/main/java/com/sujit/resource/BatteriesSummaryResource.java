package com.sujit.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@RequiredArgsConstructor
public class BatteriesSummaryResource {

  private Page page;

  private List<String> batteriesName = new ArrayList<>();

  private Double totalWattCapacity;

  private Double averageWattCapacity;

  public boolean addBattery(Optional<String> battery) {
    return this.batteriesName.add(
        battery.orElseThrow(() -> new NullPointerException("No battery is present to add")));
  }
}
