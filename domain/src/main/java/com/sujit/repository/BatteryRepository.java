package com.sujit.repository;

import com.sujit.dto.BatteryDto;
import java.util.List;
import org.springframework.data.domain.Page;

public interface BatteryRepository {

  Page<BatteryDto> persistAll(List<BatteryDto> dtos);
}
