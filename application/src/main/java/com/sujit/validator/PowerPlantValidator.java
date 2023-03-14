package com.sujit.validator;


import com.sujit.exception.Violation;

import java.util.Set;

@FunctionalInterface
public interface PowerPlantValidator<T> {

    Set<Violation> validate(T dto);
}
