package com.alvarolongueira.adventofcode.day5;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class HidrotermalPoint {

    @Value.Parameter
    public abstract int getX();

    @Value.Parameter
    public abstract int getY();

    public static HidrotermalPoint of(int x, int y) {
        return ImmutableHidrotermalPoint.of(x, y);
    }

}
