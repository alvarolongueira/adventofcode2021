package com.alvarolongueira.adventofcode.day11;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class EnergyPointLocation {

    @Value.Parameter
    public abstract int getX();

    @Value.Parameter
    public abstract int getY();

    public static EnergyPointLocation of(int x, int y) {
        return ImmutableEnergyPointLocation.of(x, y);
    }

    public boolean isValid() {
        return (this.getX() >= 0) && (this.getY() >= 0) && (this.getX() <= 9) && (this.getY() <= 9);
    }
}
