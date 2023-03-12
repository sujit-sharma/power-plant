package com.sujit.controller;

import com.sujit.dto.BatteryDto;
import com.sujit.mappper.DtoResourceMapper;
import com.sujit.resource.BatteriesSummaryResource;
import com.sujit.resource.BatterySummaryResource;
import com.sujit.service.PowerPlantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/battery")
public class BatteryController {

  private final PowerPlantService service;

  private final DtoResourceMapper mapper;

  @PostMapping(
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<List<Object>> saveBatteries(
          @RequestBody List<BatterySummaryResource> request) {


    List<BatteryDto> savedBatteries = service.saveAllBatteries(
            request.stream().map(mapper::requestToDto)
            .collect(Collectors.toList())
    );

    List<Object> response = savedBatteries.stream().map(mapper::dtoToResource).collect(Collectors.toList());

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

//  private BatteriesSummaryRequest convertToSummaryResponse(List<BatteryDto> pageDtos) {
//    BatteriesSummaryRequest battteriesSummary = new BatteriesSummaryRequest();
//
//    List<BatterySummaryResource> summaries =
//        pageDtos.stream()
//            .map(
//                battery -> {
//                  BatterySummaryResource summaryResponse = new BatterySummaryResource();
//                  summaryResponse.setName(battery.getName());
//                  summaryResponse.setPostCode(battery.getPostCode());
//                  summaryResponse.setWattCapacity(battery.getWattCapacity());
//                  return summaryResponse;
//                })
//            .collect(Collectors.toList());
//    battteriesSummary.setBatteries(summaries);
//
//    return battteriesSummary;
//  }
}
