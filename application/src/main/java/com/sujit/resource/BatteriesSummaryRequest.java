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
public class BatteriesSummaryRequest {

  private Page page;

  private List<BatterySummaryResource> batteries = new ArrayList<>();

  public boolean addBattery(Optional<BatterySummaryResource> battery) {
    return this.batteries.add(
        battery.orElseThrow(() -> new NullPointerException("No battery is present to add")));
  }
}
