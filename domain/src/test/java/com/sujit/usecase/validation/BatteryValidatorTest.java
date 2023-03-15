package com.sujit.usecase.validation;

import com.sujit.dto.BatteryDto;
import com.sujit.exception.Violation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static com.sujit.usecase.validation.Constraints.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BatteryValidatorTest {

    @InjectMocks
    private BatteryValidator validator;


    @Test
    void whenPassEmptyStringInBatteryName_thenHaveViolation() {

        BatteryDto batteryDto = new BatteryDto();
        batteryDto.setName("  ");
        batteryDto.setPostCode(56);
        batteryDto.setWattCapacity(322.0);

        Set<Violation> violations = validator.validate(batteryDto);

        assertFalse(violations.isEmpty());

    }

    @Test
    void givenNegativePostcode_whenValidate_thenShouldHaveViolation() {
        BatteryDto batteryDto = new BatteryDto();
        batteryDto.setName("name");
        batteryDto.setPostCode(-266);
        batteryDto.setWattCapacity(322.0);

        Set<Violation> violations = validator.validate(batteryDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());

        Violation resultViolation = violations.iterator().next();
        assertEquals(POSTCODE, resultViolation.getViolator());
        assertEquals(POST_CODE_SHOULD_BE_POSITIVE_INTEGER, resultViolation.getErrorMessage());

    }

    @Test
    void givenNegativeWattCapacity_whenValidate_thenShouldHaveViolation() {
        BatteryDto batteryDto = new BatteryDto();
        batteryDto.setName("name");
        batteryDto.setPostCode(266);
        batteryDto.setWattCapacity(-322.0);

        Set<Violation> violations = validator.validate(batteryDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());

        Violation resultViolation = violations.iterator().next();
        assertEquals(WATT_CAPACITY, resultViolation.getViolator());
        assertEquals(WATT_CAPACITY_SHOULD_BE_GREATER_THEN_ZERO, resultViolation.getErrorMessage());

    }

    @Test
    void givenValidBatteryDto_whenValidate_thenReturnNoViolations() {
        BatteryDto batteryDto = new BatteryDto();
        batteryDto.setName("name");
        batteryDto.setPostCode(266);
        batteryDto.setWattCapacity(322.0);

        Set<Violation> violations = validator.validate(batteryDto);

        assertTrue(violations.isEmpty());
    }



}