package com.alvarolongueira.adventofcode.day14;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class PairPolymer {

    @Value.Parameter
    public abstract String left();

    @Value.Parameter
    public abstract String right();

    public static PairPolymer of(String left, String right) {
        return ImmutablePairPolymer.of(left, right);
    }

}
