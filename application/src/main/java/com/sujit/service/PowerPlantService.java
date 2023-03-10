package com.sujit.service;

import com.sujit.dto.BatteryDto;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PowerPlantService {

  public List<BatteryDto> saveAllBatteries(List<BatteryDto> dtoList) {
    List<BatteryDto> saved =
        dtoList.stream()
            .map(
                dto -> {
                  dto.setBatteryId(UUID.randomUUID().toString());
                  return dto;
                })
            .collect(Collectors.toList());

    return null;
  }
}
