package com.sujit.validator.usecase;

import java.util.function.Function;

@FunctionalInterface
public interface UseCase<T, R> extends Function<T, Void> {

    Void execute(T subject);

    @Override
    default Void apply(T t) {
        return execute(t);
    }
}
