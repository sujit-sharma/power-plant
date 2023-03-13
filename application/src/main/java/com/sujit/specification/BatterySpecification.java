package com.sujit.specification;

import com.sujit.entity.BatteryEntity;
import com.sujit.exception.DomainViolationException;
import com.sujit.exception.Violation;
import com.sujit.validator.QueryParamValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;


@Slf4j
public class BatterySpecification {

    private final Set<Violation> violations = new HashSet<>();

    private final List<BiFunction<Root<BatteryEntity>, CriteriaBuilder, Predicate>> predicates = new ArrayList<>(2);

    public Specification<BatteryEntity> buildSpecification() {
        throwIfViolationExist();

        return (root, query, criteriaBuilder) -> {
            Predicate[] predicateArray = predicates.stream().map(p -> p.apply(root, criteriaBuilder))
                    .toArray(Predicate[]::new);
            return criteriaBuilder.and(predicateArray);
        };
    }

    public static BatterySpecification of() {
        return new BatterySpecification();
    }

    private void addToPredicate(
            BiFunction<Root<BatteryEntity>, CriteriaBuilder, Predicate> predicateBiFunction) {
        predicates.add(predicateBiFunction);
    }

    public BatterySpecification postCodeRange(Integer from, Integer to, String field) {
        QueryParamValidator.validatePostalCodeRange(violations, from, to, field);
        if (violations.isEmpty()) {
            addToPredicate(
                    (batteryEntityRoot, criteriaBuilder) -> criteriaBuilder.between(batteryEntityRoot.get(field), from, to));
        }

        return this;
    }


    private void throwIfViolationExist() {
        if (!violations.isEmpty()) {
            throw new DomainViolationException(violations);
        }
    }

}
