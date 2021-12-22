package com.alvarolongueira.adventofcode.day15;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class CavePoint {

    @Value.Parameter
    public abstract CavePointPosition position();

    @Value.Parameter
    public abstract int cost();

    public static CavePoint of(int x, int y, int value) {
        return ImmutableCavePoint.of(CavePointPosition.of(x, y), value);
    }

}
