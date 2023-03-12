package com.sujit.exception;

import java.io.Serializable;
import java.util.Objects;

public class SimpleViolation implements Violation, Serializable {

    private String violator;

    private String errorMessage;

    public SimpleViolation(String violator, String errorMessage) {
        this.violator = violator;
        this.errorMessage = errorMessage;
    }

    public SimpleViolation() {

    }

    @Override
    public String getViolator() {
        return violator;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleViolation that = (SimpleViolation) o;
        return Objects.equals(violator, that.violator) && Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(violator, errorMessage);
    }
}
