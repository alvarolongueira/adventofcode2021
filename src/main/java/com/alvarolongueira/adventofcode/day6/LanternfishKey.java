package com.alvarolongueira.adventofcode.day6;

import org.immutables.value.Value;

@Value.Immutable
public abstract class LanternfishKey {

    @Value.Parameter
    public abstract int getKeyTime();

    public static LanternfishKey of(int keyTime) {
        return ImmutableLanternfishKey.of(keyTime);
    }

    @Override
    public String toString() {
        return String.valueOf(this.getKeyTime());
    }

}
