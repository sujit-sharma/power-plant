package com.sujit.service;

import com.sujit.dto.BatteryDto;
import com.sujit.repository.BatteryRepositoryAdaptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PowerPlantService {

    private final BatteryRepositoryAdaptor repository;

  public List<BatteryDto> saveAllBatteries(List<BatteryDto> dtoList) {
    List<BatteryDto> batteries =
        dtoList.stream()
            .map(
                dto -> {
                  dto.setBatteryId(UUID.randomUUID().toString());
                  return dto;
                })
            .collect(Collectors.toList());

    log.info("Batteries Saves Success!");
    return repository.persistAll(batteries);

  }
}
