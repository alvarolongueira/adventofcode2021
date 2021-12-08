package com.alvarolongueira.adventofcode.day8;

import java.util.List;

import org.immutables.value.Value;

@Value.Immutable
public abstract class DisplayOutputResult {

    public static class Builder extends ImmutableDisplayOutputResult.Builder {

    }

    public static Builder builder() {
        return new Builder();
    }

    public abstract DisplayOutput getDisplayOutput();

    public abstract List<String> getControlDigits();

    public abstract List<String> getOuput();

    public long value() {
        String chain = "0";
        for (String output : this.getOuput()) {
            chain += output;
        }
        return Long.valueOf(chain);
    }

    public static long sumValues(List<DisplayOutputResult> results) {
        return results.stream().mapToLong(result -> result.value()).sum();
    }
}
