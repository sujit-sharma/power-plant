package com.sujit.usecase.validation;

import com.sujit.exception.SimpleViolation;
import com.sujit.exception.Violation;

import java.util.Set;

import static com.sujit.usecase.validation.Constraints.*;

public class QueryParamValidator {

    public static void validatePostalCodeRange(Set<Violation> violations, Integer from, Integer to, String field) {
        if (from < 0 || to < 0) {
            violations.add(buildViolation(field, START_AND_END_OF_RANGE_MUST_BE_POSITIVE));
        }
        if (from> 5000 || to > 5000) {
            violations.add(buildViolation(field, START_AND_END_OF_RANGE_MUST_BE_POSITIVE_AND_LESS_THEN_5000));
        }
        if (from > to) {
            violations.add(buildViolation(field, START_VALUE_MUST_BE_LESS_THEN_END));
        }
    }

    private static SimpleViolation buildViolation(String field, String message) {
        return new SimpleViolation(field, message);
    }
}
