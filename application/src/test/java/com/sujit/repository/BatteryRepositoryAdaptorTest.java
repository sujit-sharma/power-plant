package com.sujit.repository;

import com.sujit.dto.BatteryDto;
import com.sujit.entity.BatteryEntity;
import com.sujit.exception.DomainViolationException;
import com.sujit.mappper.BatteryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BatteryRepositoryAdaptorTest {

    @InjectMocks
    private BatteryRepositoryAdaptor adaptor;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private DataLayerBatteryRepository repository;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private BatteryMapper mapper;


    @Test
    void givenBatteryDtoList_whenPersist_ShouldSaveandReturnSavedDtos() {
        List<BatteryDto> dtos = new ArrayList<>();
        dtos.add(new BatteryDto());
        dtos.add(new BatteryDto());
        List<BatteryEntity> entities = new ArrayList<>();
        entities.add(new BatteryEntity());
        entities.add(new BatteryEntity());

        when(repository.saveAll(anyList())).thenReturn(entities);

        List<BatteryDto> result = adaptor.persistAll(dtos);

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        verify(repository).saveAll(anyList());
        verify(mapper, times(2)).entityToDto(any(BatteryEntity.class));

    }

    @Test
    void givenEmptyList_whenPersist_ShouldNotThrowAnyException() {
        List<BatteryDto> dtos = new ArrayList<>();
        List<BatteryEntity> entities = new ArrayList<>();
        when(repository.saveAll(anyList())).thenReturn(entities);

        List<BatteryDto> result = adaptor.persistAll(dtos);

        assertTrue(result.isEmpty());

    }

    @Test
    void givenValidRange_whenExecute_ShouldReturnDtosThatLiesInTheRange () {

        List<BatteryEntity> entities = this.createBatteryEntities();
        when(repository.findAll(any(Specification.class))).thenReturn(entities);
        List<BatteryDto> result = adaptor.findAllByPostCodeRange(2, 200);

        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        verify(mapper, times(3)).entityToDto(any(BatteryEntity.class));

    }

    @Test
    void givenInvalidRange_whenFindByRange_ShouldThrowDomainViolationException() {
        Exception exception = assertThrows(DomainViolationException.class, () -> {
            adaptor.findAllByPostCodeRange(-1, 200);
        });
        String expectedErrorMessage = "The Start and end values of range must be both positive";

        assertEquals(expectedErrorMessage, exception.getMessage());

    }


    private List<BatteryEntity> createBatteryEntities() {

        List<BatteryEntity> batteryEntities = new ArrayList<>();
        BatteryEntity battery1 = new BatteryEntity();
        battery1.setBatteryId(UUID.randomUUID().toString());
        battery1.setName("battery1");
        battery1.setPostCode(4);

        BatteryEntity battery2 = new BatteryEntity();
        battery2.setBatteryId(UUID.randomUUID().toString());
        battery2.setName("battery2");
        battery2.setPostCode(44);

        BatteryEntity battery3  = new BatteryEntity();
        battery3.setBatteryId(UUID.randomUUID().toString());
        battery3.setName("battery3");
        battery3.setPostCode(120);

        batteryEntities.addAll(Arrays.asList(battery1, battery2, battery3));
        return batteryEntities;
    }


}