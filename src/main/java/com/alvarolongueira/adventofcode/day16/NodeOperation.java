package com.alvarolongueira.adventofcode.day16;

import java.util.List;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class NodeOperation {

    @Value.Parameter
    public abstract String type();

    @Value.Parameter
    public abstract List<String> values();

    public static NodeOperation of(String type, List<String> values) {
        return ImmutableNodeOperation.of(type, values);
    }

}
