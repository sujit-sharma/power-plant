package com.sujit.usecase;

import com.sujit.dto.BatteryDto;
import com.sujit.exception.DomainViolationException;
import com.sujit.exception.SimpleViolation;
import com.sujit.exception.Violation;
import com.sujit.usecase.validation.BatteryValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BatteryUseCaseTest {

    @InjectMocks
    private BatteryUseCase useCase;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private BatteryValidator validator;


    @Test
    void givenInvalidDto_whenExecute_ShouldThrowException(){
        List<BatteryDto> dtos = new ArrayList<>();
        dtos.add(new BatteryDto());
        Set<Violation> violations = new HashSet<>();

        violations.add(new SimpleViolation("key", "message"));

        when(validator.validate(ArgumentMatchers.any(BatteryDto.class))).thenReturn(violations);

        Assertions.assertThrows(DomainViolationException.class, () -> useCase.execute(dtos));
        verify(validator).validate(ArgumentMatchers.eq(dtos.get(0)));

    }

    @Test
    void givenValidBatteryDto_whenExecute_thenCompleteValidationProcess() {
        List<BatteryDto> dtoList = new ArrayList<>();
        dtoList.add(new BatteryDto());
        dtoList.add(new BatteryDto());


        when(validator.validate(ArgumentMatchers.any(BatteryDto.class))).thenReturn(new HashSet<>());

        useCase.execute(dtoList);

        verify(validator, times(2)).validate(ArgumentMatchers.any(BatteryDto.class));
    }
}