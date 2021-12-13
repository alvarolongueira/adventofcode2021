package com.alvarolongueira.adventofcode.day12;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class CaveConnection {

    @Value.Parameter
    public abstract CaveCode getSource();

    @Value.Parameter
    public abstract CaveCode getTarget();

    public static CaveConnection of(CaveCode source, CaveCode target) {
        return ImmutableCaveConnection.of(source, target);
    }

}
