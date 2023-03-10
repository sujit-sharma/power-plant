package com.sujit.repository;

import com.sujit.dto.BatteryDto;
import java.util.List;

public interface BatteryRepository {

  List<BatteryDto> persistAll(List<BatteryDto> dtos);
}
