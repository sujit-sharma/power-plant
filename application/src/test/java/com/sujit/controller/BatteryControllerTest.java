package com.sujit.controller;

import com.sujit.dto.BatteriesSummaryDto;
import com.sujit.dto.BatteryDto;
import com.sujit.mappper.DtoResourceMapper;
import com.sujit.resource.BatteriesSummaryResource;
import com.sujit.resource.BatterySummaryResource;
import com.sujit.service.PowerPlantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BatteryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private BatteryController controller;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private PowerPlantService service;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private DtoResourceMapper mapper;

    @Test
    void givenBatteriesList_whenExecuted_thenShouldReturnSavedResource() {
        List<BatterySummaryResource> request = this.creatRequest();
        List<BatteryDto> dtos = this.convertToDto(request);

        when(service.saveAllBatteries(anyList())).thenReturn(dtos);
        when(mapper.dtoToResource(any(BatteryDto.class))).thenReturn(this.convertToResource(dtos.get(0)));

        ResponseEntity<List<BatterySummaryResource>> response = controller.saveBatteries(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("battery1", response.getBody().get(0).getName());

        verify(mapper, times(1)).dtoToResource(any(BatteryDto.class));

    }

    @Test
    void givenPostCodeRange_whenFind_thenShouldReturnBatteriesSummaryWithStatus200() {
        BatteriesSummaryDto summaryDto = new BatteriesSummaryDto();
        summaryDto.setBatteriesName(Arrays.asList("battery1", "battery2", "battery3"));
        summaryDto.setAverageWattCapacity(25.0);
        summaryDto.setTotalWattCapacity(100.0);

        when(service.findByPostCodeRange(anyInt(), anyInt())).thenReturn(summaryDto);

        ResponseEntity<BatteriesSummaryResource> response = controller.findByPostalCodeRange(2, 200);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody().getBatteriesName().size());
        assertEquals(100.0, response.getBody().getTotalWattCapacity());
    }

    private List<BatterySummaryResource> creatRequest(){
        List<BatterySummaryResource> resources = new ArrayList<>();

        BatterySummaryResource resource1 = new BatterySummaryResource();
        resource1.setName("battery1");
        resource1.setPostCode(4);

        resources.add(resource1);

        return resources;
    }

    private List<BatteryDto> convertToDto(List<BatterySummaryResource> request ) {
           return request.stream().map(req -> {
               BatteryDto dto = new BatteryDto();
               dto.setName(req.getName());
               dto.setPostCode(req.getPostCode());
               return dto;
           }).collect(Collectors.toList());
    }

    private BatterySummaryResource convertToResource(BatteryDto dto) {
            BatterySummaryResource resource = new BatterySummaryResource();
            resource.setName(dto.getName());
            resource.setPostCode(dto.getPostCode());
            return resource;
    }
}