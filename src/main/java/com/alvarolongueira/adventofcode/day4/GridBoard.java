package com.alvarolongueira.adventofcode.day4;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class GridBoard {

    @Value.Parameter
    public abstract int getX();

    @Value.Parameter
    public abstract int getY();

    public static GridBoard of(int x, int y) {
        return ImmutableGridBoard.of(x, y);
    }

}
