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
    public abstract CavePoint source();

    @Value.Parameter
    public abstract CavePoint last();

    public static CavePointPath of(int cost, int maxPos, CavePoint position) {
        return ImmutableCavePointPath.of(cost, maxPos, false, position, position);
    }

    public CavePointPath add(CavePoint newPosition) {
        return CavePointPath.of(this.cost(), this.maxPos(), this.last(), newPosition);
    }

    private static CavePointPath of(int cost, int maxPos, CavePoint oldPosition, CavePoint newPosition) {
        int newCost = cost + newPosition.cost();
        boolean isEnd = newPosition.position().getX() >= maxPos && newPosition.position().getY() >= maxPos;
        return ImmutableCavePointPath.of(newCost, maxPos, isEnd, oldPosition, newPosition);
    }

}
