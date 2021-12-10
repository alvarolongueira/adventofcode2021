package com.alvarolongueira.adventofcode.day10;

import java.util.List;

import org.immutables.value.Value;

@Value.Immutable
public abstract class BracketList {

    public static BracketList of(List<Bracket> list) {
        return ImmutableBracketList.of(list);
    }

    @Value.Parameter
    public abstract List<Bracket> getBrackets();

    public String toStringCloseList() {
        return this.getBrackets().stream()
                .map(value -> value.getClose())
                .reduce((a, b) -> a + b).get();
    }

    public long score() {
        long score = 0;
        for (Bracket bracket : this.getBrackets()) {
            score = score * 5;
            score += bracket.getScore();
        }
        return score;
    }
}
