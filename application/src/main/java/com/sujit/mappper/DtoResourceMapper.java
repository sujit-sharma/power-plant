 package com.sujit.mappper;


 import com.sujit.dto.BatteryDto;
 import com.sujit.entity.BatteryEntity;
 import com.sujit.resource.BatteriesSummaryRequest;
 import com.sujit.resource.BatterySummaryResource;
 import org.mapstruct.Mapper;
 import org.mapstruct.Mapping;
 import org.mapstruct.Mappings;


 @Mapper(componentModel = "spring")
 public interface DtoResourceMapper {

  @Mappings({@Mapping(target = "batteryId", ignore = true)})
  BatteryDto requestToDto(BatterySummaryResource request);

  BatterySummaryResource dtoToRequest(BatteryDto dto);

 }
