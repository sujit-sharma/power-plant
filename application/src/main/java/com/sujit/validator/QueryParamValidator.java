package com.sujit.validator;

import com.sujit.exception.SimpleViolation;
import com.sujit.exception.Violation;

import java.util.Set;

public class QueryParamValidator {

    public static void validatePostalCodeRange(Set<Violation> violations, Integer from, Integer to, String field) {
        if (from > to) {
            violations.add(buildViolation(field, "Start range should be less then to range"));
        }
    }

    private static SimpleViolation buildViolation(String field, String message) {
        return new SimpleViolation(field, message);
    }
}
