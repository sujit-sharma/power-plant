package com.sujit.repository;

import com.sujit.dto.BatteryDto;
import com.sujit.entity.BatteryEntity;
import com.sujit.mappper.BatteryMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class BatteryRepositoryAdaptor implements BatteryRepository {

  private final DataLayerBatteryRepository dataLayerBatteryRepository;

  private final BatteryMapper mapper;

  @Override
  public Page<BatteryDto> persistAll(List<BatteryDto> dtos) {
    Page<BatteryEntity> savedEntities =
        this.dataLayerBatteryRepository.saveAll(
            dtos.stream()
                .map(
                    dto -> {
                      BatteryEntity entity = new BatteryEntity();
                      entity.setBatteryId(dto.getBatteryId());
                      entity.setName(dto.getName());
                      entity.setPostCode(dto.getPostCode());
                      entity.setWattCapacity(dto.getWattCapacity());
                      return entity;
                    })
                .collect(Collectors.toList()));

    return null;
  }
}
