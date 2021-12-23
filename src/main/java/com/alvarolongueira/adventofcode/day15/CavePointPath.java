package com.alvarolongueira.adventofcode.day15;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class CavePointPath {

    @Value.Parameter
    public abstract int cost();

    @Value.Parameter
    public abstract int maxPos();

    @Value.Parameter
    public abstract boolean isEnd();

    @Value.Parameter
    public abstract CavePoint last();

    public static CavePointPath of(int maxPos, CavePoint position) {
        return ImmutableCavePointPath.of(0, maxPos, false, position);
    }

    public CavePointPath add(CavePoint newPosition) {
        int newCost = this.cost() + newPosition.cost();
        boolean isEnd = newPosition.position().getX() >= this.maxPos() && newPosition.position().getY() >= this.maxPos();
        return ImmutableCavePointPath.of(newCost, this.maxPos(), isEnd, newPosition);
    }

}
