package com.sujit.exception;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ValidatorResponse {

    private final Set<Violation> violations = new HashSet<>();

    public ValidatorResponse(Set<Violation> violations) {
        if (!Objects.isNull(violations)) {
            this.violations.addAll(violations);
        }
    }

    public boolean isViolated() {
        return !this.violations.isEmpty();
    }

    public int size() {
        return this.violations.size();
    }

    public void ifViolationThenThrowException() {
        if (this.isViolated()) {
            throw new DomainViolationException(this.violations);
        }
    }

    public void addViolations(Set<Violation> violations) {
        this.violations.addAll(violations);
    }

    public void addViolation (Violation violation) {
        this.violations.add(violation);
    }

    public Set<Violation> getViolations() {
        return Collections.unmodifiableSet(this.violations);
    }
}
