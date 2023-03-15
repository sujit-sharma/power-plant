package com.sujit.usecase.validation;

import com.sujit.dto.BatteryDto;
import com.sujit.exception.SimpleViolation;
import com.sujit.exception.Violation;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.sujit.usecase.validation.Constraints.*;

@AllArgsConstructor
@Component
public class BatteryValidator implements PowerPlantValidator<BatteryDto> {

    @Override
    public Set<Violation> validate(BatteryDto dto) {
        Set<Violation> violations = new HashSet<>();
        violations.addAll(validateName(dto));
        violations.addAll(validatePostCode(dto));
        violations.addAll(validateWattCapacity(dto));
        return violations;
    }

    private Set<Violation> validateName(BatteryDto dto) {
        Set<Violation> violations = new HashSet<>();
        if (Objects.isNull(dto.getName()) || Strings.isBlank(dto.getName())) {
            violations.add(new SimpleViolation(BATTERY_NAME, BATTERY_NAME_SHOULD_NOT_BE_EMPTY));
        }
        return violations;
    }

    private Set<Violation> validatePostCode(BatteryDto dto) {
        Set<Violation> violations = new HashSet<>();
        if (dto.getPostCode() < 0 ) {
            violations.add(new SimpleViolation(POSTCODE, POST_CODE_SHOULD_BE_POSITIVE_INTEGER));
        }
        return violations;

    }

    private Set<Violation> validateWattCapacity(BatteryDto dto) {
        Set<Violation> violations = new HashSet<>();
        if (dto.getWattCapacity() < 0) {
            violations.add(new SimpleViolation(WATT_CAPACITY, WATT_CAPACITY_SHOULD_BE_GREATER_THEN_ZERO));
        }
        if (dto.getWattCapacity() > 5000) {
            violations.add(new SimpleViolation(WATT_CAPACITY, WATT_CAPACITY_SHOULD_BE_LESS_THEN_5000));
        }
        return violations;
    }
}
