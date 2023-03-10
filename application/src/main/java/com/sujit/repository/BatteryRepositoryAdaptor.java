package com.sujit.repository;

import com.sujit.dto.BatteryDto;
import com.sujit.entity.BatteryEntity;
import com.sujit.mappper.BatteryMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
@Getter
public class BatteryRepositoryAdaptor implements BatteryRepository {

  private final DataLayerBatteryRepository dataLayerBatteryRepository;

  private final BatteryMapper mapper;

  @Override
  public List<BatteryDto> persistAll(List<BatteryDto> dtos) {

    Iterable<BatteryEntity> savedEntities =
        this.dataLayerBatteryRepository.saveAll(
            dtos.stream()
                .map(batteryDto -> mapper.dtoToEntity(batteryDto))
                .collect(Collectors.toList())
        );
    return StreamSupport.stream(savedEntities.spliterator(), false)
            .map(mapper::entityToDto)
            .collect(Collectors.toList());
  }
}
