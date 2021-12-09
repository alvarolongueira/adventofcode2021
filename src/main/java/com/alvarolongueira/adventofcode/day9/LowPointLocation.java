package com.alvarolongueira.adventofcode.day9;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class LowPointLocation {

    @Value.Parameter
    public abstract int getX();

    @Value.Parameter
    public abstract int getY();

    public static LowPointLocation of(int x, int y) {
        return ImmutableLowPointLocation.of(x, y);
    }

}
