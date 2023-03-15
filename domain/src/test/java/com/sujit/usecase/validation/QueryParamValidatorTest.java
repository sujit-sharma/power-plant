package com.sujit.usecase.validation;

import com.sujit.exception.Violation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.sujit.usecase.validation.Constraints.START_VALUE_MUST_BE_LESS_THEN_END;
import static org.junit.jupiter.api.Assertions.*;

class QueryParamValidatorTest {

    private Set<Violation> violations;
    private static final String FIELD_NAME = "postCode";

    @BeforeEach
    void setUp() {
        violations = new HashSet<>();
    }

    @Test
    void givenEndValueLessThenStart_whenValidate_thenShouldAddViolation() {

        QueryParamValidator.validatePostalCodeRange(violations, 200, 100, FIELD_NAME);

        assertEquals(1, violations.size());
        assertEquals(FIELD_NAME, violations.iterator().next().getViolator());
        assertEquals(START_VALUE_MUST_BE_LESS_THEN_END, violations.iterator().next().getErrorMessage());
    }

    @Test
    void givenInvalidStartPostCode_whenValidate_thenShouldAddViolation() {

        QueryParamValidator.validatePostalCodeRange(violations, -23, 34, QueryParamValidatorTest.FIELD_NAME);

        assertEquals(1, violations.size());
        assertEquals(QueryParamValidatorTest.FIELD_NAME, violations.iterator().next().getViolator());
    }

    @Test
    void givenInvalidRangeValues_whenValidate_thenShouldAddViolation() {

        QueryParamValidator.validatePostalCodeRange(violations, -23, 6000, QueryParamValidatorTest.FIELD_NAME);

        assertEquals(2, violations.size());
        assertEquals(QueryParamValidatorTest.FIELD_NAME, violations.iterator().next().getViolator());
    }

}