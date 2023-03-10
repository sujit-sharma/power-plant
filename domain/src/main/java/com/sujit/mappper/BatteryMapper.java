package com.sujit.mappper;

import com.sujit.dto.BatteryDto;
import com.sujit.entity.BatteryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BatteryMapper {

  BatteryDto entityToDto(BatteryEntity entity);

  BatteryEntity dtoToEntity(BatteryDto dto);

}
