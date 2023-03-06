package com.sujit.powerplant.controller;

import com.sujit.powerplant.resource.BatteriesSummaryRequest;
import com.sujit.powerplant.resource.BatterySummaryRequest;
import com.sujit.powerplant.service.PowerPlantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/battery")
public class BatteryController {

    private final PowerPlantService service;

    @PostMapping(
            produces = {"application/json"},
            consumes = {"application/json"}
    )

    public ResponseEntity<BatteriesSummaryRequest> saveBatteries(@RequestBody BatteriesSummaryRequest request) {

        return new ResponseEntity<>(service.saveAllBatteries(request), HttpStatus.OK);

    }

}
