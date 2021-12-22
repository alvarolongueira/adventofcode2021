package com.alvarolongueira.adventofcode.day15;

import java.util.ArrayList;
import java.util.List;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class CavePointPath {

    @Value.Parameter
    public abstract long id();

    @Value.Parameter
    public abstract int max();

    @Value.Parameter
    public abstract int cost();

    @Value.Parameter
    public abstract boolean hasLast();

    @Value.Parameter
    public abstract CavePoint last();

    @Value.Parameter
    public abstract List<CavePoint> positions();

    public static CavePointPath of(long id, int cost, int max, List<CavePoint> positions) {
        CavePoint last = positions.get(positions.size() - 1);
        boolean hasLast = last.position().getX() >= max && last.position().getY() >= max;

        if (positions.size() > 20) {
            positions = positions.subList(10, positions.size());
        }
        return ImmutableCavePointPath.of(id, max, cost, hasLast, last, positions);
    }

    public CavePointPath add(long id, CavePoint position) {
        int cost = this.cost() + position.cost();
        List<CavePoint> list = new ArrayList<>(this.positions());
        list.add(position);
        return CavePointPath.of(id, cost, this.max(), list);
    }

}
