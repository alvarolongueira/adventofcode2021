package com.alvarolongueira.adventofcode.day10;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.immutables.value.Value;

@Value.Immutable
public abstract class ChunkBracketResult {

    public static class Builder extends ImmutableChunkBracketResult.Builder {

    }

    public static Builder builder() {
        return new Builder();
    }

    public abstract HashMap<Bracket, Integer> getBracketErrors();

    public abstract List<BracketList> getBracketMissed();

    public long sumErrors() {
        return this.getBracketErrors().entrySet().stream()
                .mapToLong(entry -> entry.getKey().getCost() * entry.getValue())
                .sum();
    }

    public long scoreFromMissed() {
        List<Long> scores = this.getBracketMissed().stream()
                .map(current -> current.score())
                .sorted()
                .collect(Collectors.toList());

        int midium = Math.floorDiv(scores.size(), 2);
        return scores.get(midium);
    }

}
