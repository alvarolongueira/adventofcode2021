package com.alvarolongueira.adventofcode.day13;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class FoldPaperPosition {

    @Value.Parameter
    public abstract int getX();

    @Value.Parameter
    public abstract int getY();

    public static FoldPaperPosition of(int x, int y) {
        return ImmutableFoldPaperPosition.of(x, y);
    }

    public boolean isValid() {
        return (this.getX() >= 0) && (this.getY() >= 0);
    }

}
