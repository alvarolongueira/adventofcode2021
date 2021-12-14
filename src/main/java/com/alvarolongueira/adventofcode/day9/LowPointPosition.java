package com.alvarolongueira.adventofcode.day9;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class LowPointPosition {

    @Value.Parameter
    public abstract int getX();

    @Value.Parameter
    public abstract int getY();

    public static LowPointPosition of(int x, int y) {
        return ImmutableLowPointPosition.of(x, y);
    }

}
