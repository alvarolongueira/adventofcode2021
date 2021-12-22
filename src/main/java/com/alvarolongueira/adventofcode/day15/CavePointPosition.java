package com.alvarolongueira.adventofcode.day15;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class CavePointPosition {

    @Value.Parameter
    public abstract int getX();

    @Value.Parameter
    public abstract int getY();

    public static CavePointPosition of(int x, int y) {
        return ImmutableCavePointPosition.of(x, y);
    }

    @Override
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }

}
