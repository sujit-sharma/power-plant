package com.sujit.service;

import com.sujit.dto.BatteriesSummaryDto;
import com.sujit.dto.BatteryDto;
import com.sujit.repository.BatteryRepositoryAdaptor;
import com.sujit.usecase.BatteryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PowerPlantService {

    private final BatteryRepositoryAdaptor repository;

    private final BatteryUseCase useCase;
  public List<BatteryDto> saveAllBatteries(List<BatteryDto> dtoList) {
      useCase.execute(dtoList);
    List<BatteryDto> batteries =
        dtoList.stream()
            .map(
                dto -> {
                  dto.setBatteryId(UUID.randomUUID().toString());
                  dto.setCreatedDate(LocalDateTime.now());
                  dto.setLastModifiedDate(LocalDateTime.now());
                  return dto;
                })
            .collect(Collectors.toList());

    log.info("Batteries Saves Success!");
    return repository.persistAll(batteries);

  }

  public BatteriesSummaryDto findByPostCodeRange(Integer from, Integer to) {
      List<BatteryDto> dtos = repository.findAllByPostCodeRange(from, to);

      BatteriesSummaryDto batteriesSummary = new BatteriesSummaryDto();
      batteriesSummary.setBatteriesName(dtos.stream().map(dto -> dto.getName()).collect(Collectors.toList()));

      Double totalWattCapacity = dtos.stream()
              .map(BatteryDto::getWattCapacity)
              .reduce(0.0, (total, element) -> total + element);
      batteriesSummary.setTotalWattCapacity(totalWattCapacity);
      batteriesSummary.setAverageWattCapacity(totalWattCapacity/dtos.size());

      return batteriesSummary;
  }
}
