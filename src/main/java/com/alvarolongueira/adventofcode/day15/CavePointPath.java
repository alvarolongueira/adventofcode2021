package com.alvarolongueira.adventofcode.day15;

import java.util.ArrayList;
import java.util.List;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class CavePointPath {

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

    public static CavePointPath of(int cost, int max, List<CavePoint> positions) {
        CavePoint last = positions.get(positions.size() - 1);
        boolean hasLast = last.position().getX() >= max && last.position().getY() >= max;
        return ImmutableCavePointPath.of(max, cost, hasLast, last, positions);
    }

    public CavePointPath add(CavePoint position) {
        int cost = this.cost() + position.cost();
        List<CavePoint> list = new ArrayList<>(this.positions());
        list.add(position);
        if (list.size() > 20) {
            list = list.subList(10, list.size());
        }
        return CavePointPath.of(cost, this.max(), list);
    }

}
