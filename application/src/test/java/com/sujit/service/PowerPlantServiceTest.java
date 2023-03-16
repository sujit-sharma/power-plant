package com.sujit.service;

import com.sujit.dto.BatteriesSummaryDto;
import com.sujit.dto.BatteryDto;
import com.sujit.repository.BatteryRepositoryAdaptor;
import com.sujit.usecase.BatteryUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PowerPlantServiceTest {

    @InjectMocks
    private PowerPlantService service;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private BatteryRepositoryAdaptor repository;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private BatteryUseCase useCase;

    @Test
    void givenBatteryList_whenSaved_shouldSaveAndReturnSavedBatteries() {
        List<BatteryDto> batteryDtos = this.createBatteryDtos();
        when(repository.persistAll(batteryDtos)).thenReturn(batteryDtos);

        List<BatteryDto> savedDtos = service.saveAllBatteries(batteryDtos);

        assertEquals(3, savedDtos.size());
        verify(useCase).execute(batteryDtos);
    }

    @Test
    void givenPostCodeRange_whenexecute_shouldReturnBattriesNameWithAvgTotalWattCapacity() {
        List<BatteryDto> batteryDtos = this.createBatteryDtos();
        when(repository.findAllByPostCodeRange(anyInt(), anyInt())).thenReturn(batteryDtos);

        BatteriesSummaryDto result  = service.findByPostCodeRange(2, 400);

        assertNotNull(result);
        assertTrue(result.getBatteriesName().containsAll(Arrays.asList("battery1","battery2", "battery3")));
        assertEquals(17.0, result.getTotalWattCapacity());
        assertEquals(5, result.getAverageWattCapacity().intValue());
    }

    @Test
    void whenMockReturnsEmptyList_shouldHaveAverageAndTotalCapacityZero() {
        when(repository.findAllByPostCodeRange(anyInt(), anyInt())).thenReturn(new ArrayList<>());

        BatteriesSummaryDto result  = service.findByPostCodeRange(2, 400);

        assertNotNull(result);
        assertTrue(result.getBatteriesName().isEmpty());
        assertEquals(0.0, result.getTotalWattCapacity());
        assertEquals(0, result.getAverageWattCapacity().intValue());
    }



    private List<BatteryDto> createBatteryDtos() {
        List<BatteryDto> batteryDtoList = new ArrayList<>();
        BatteryDto battery1  = new BatteryDto();
        battery1.setName("battery1");
        battery1.setPostCode(34);
        battery1.setWattCapacity(4.0);

        BatteryDto battery2 = new BatteryDto();
        battery2.setName("battery2");
        battery2.setPostCode(45);
        battery2.setWattCapacity(6.0);


        BatteryDto battery3 = new BatteryDto();
        battery3.setName("battery3");
        battery3.setPostCode(45);
        battery3.setWattCapacity(7.0);

        batteryDtoList.addAll(Arrays.asList(battery1, battery2, battery3));
        return batteryDtoList;
    }

}