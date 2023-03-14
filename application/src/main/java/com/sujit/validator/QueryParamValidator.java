package com.sujit.validator;

import com.sujit.exception.SimpleViolation;
import com.sujit.exception.Violation;

import java.util.Set;

public class QueryParamValidator {

    public static void validatePostalCodeRange(Set<Violation> violations, Integer from, Integer to, String field) {
        if (from > to) {
            violations.add(buildViolation(field, "Start value should be less then end value for range"));
        }
    }

    private static SimpleViolation buildViolation(String field, String message) {
        return new SimpleViolation(field, message);
    }
}
