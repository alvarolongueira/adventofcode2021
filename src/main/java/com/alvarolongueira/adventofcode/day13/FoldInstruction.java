package com.alvarolongueira.adventofcode.day13;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class FoldInstruction {

    @Value.Parameter
    public abstract String getDirection();

    @Value.Parameter
    public abstract int getValue();

    public static FoldInstruction of(String direction, int value) {
        return ImmutableFoldInstruction.of(direction, value);
    }

    public boolean isDirectionX() {
        return this.getDirection().equalsIgnoreCase("x");
    }

    public boolean isDirectionY() {
        return !this.isDirectionX();
    }
}
