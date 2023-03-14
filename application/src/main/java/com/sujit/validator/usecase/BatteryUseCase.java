package com.sujit.validator.usecase;

import com.sujit.dto.BatteryDto;
import com.sujit.exception.DomainViolationException;
import com.sujit.exception.Violation;
import com.sujit.validator.BatteryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class BatteryUseCase implements UseCase<List<BatteryDto>, Void> {

    private final BatteryValidator validator;

    @Override
    public Void execute(List<BatteryDto> subject) {
        validateTechnically(subject);
        return null;
    }

    private void validateTechnically(List<BatteryDto> dtoList) {
        Set<Violation> violations = new HashSet<>();
        dtoList.forEach(dto -> {
            violations.addAll(validator.validate(dto));

        });

        if (!violations.isEmpty()) {
            throw new DomainViolationException(violations);
        }
    }
}
