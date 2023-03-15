package com.sujit.exception;

import java.util.*;

public class DomainViolationException extends RuntimeException {

    private final Set<Violation> violations = new HashSet<>();

    public DomainViolationException(Set<Violation> violations) {
        super(getViolationsAsString(violations.stream().map(Violation::getErrorMessage)
        .reduce((e1, e2) -> {
            return e1 + ", " + e2;
        })));

        if (!Objects.isNull(violations)) {
            this.violations.addAll(violations);
        }
    }

    public DomainViolationException(String violator, String message) {
        super(message);
        this.violations.addAll(Collections.singleton(new SimpleViolation(violator, message)));
    }

    public Set<Violation> getViolations() {
        return Collections.unmodifiableSet(this.violations);
    }

    private static String  getViolationsAsString(Optional<String> violation) {
        return (String) violation.orElse("");
    }
}
