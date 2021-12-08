package com.alvarolongueira.adventofcode.day8;

import java.util.List;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class DisplayOutput {

    @Value.Parameter
    public abstract List<String> getControlDigits();

    @Value.Parameter
    public abstract List<String> getOutput();

    public static DisplayOutput of(List<String> controlDigits, List<String> output) {
        return ImmutableDisplayOutput.of(controlDigits, output);
    }

}
