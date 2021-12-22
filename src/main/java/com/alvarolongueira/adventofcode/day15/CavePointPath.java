package com.alvarolongueira.adventofcode.day15;

import java.util.ArrayList;
import java.util.List;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class CavePointPath {

    @Value.Parameter
    public abstract int cost();

    @Value.Parameter
    public abstract int maxPos();

    @Value.Parameter
    public abstract boolean hasLast();

    @Value.Parameter
    public abstract CavePoint last();

    @Value.Parameter
    public abstract List<CavePoint> positions();

    public static CavePointPath of(int cost, int maxPos, CavePoint position) {
        List<CavePoint> list = new ArrayList<>();
        list.add(position);
        return ImmutableCavePointPath.of(cost, maxPos, false, position, list);
    }

    public CavePointPath add(CavePoint newPosition) {
        return CavePointPath.of(this.cost(), this.maxPos(), newPosition, this.positions());
    }

    private static CavePointPath of(int cost, int maxPos, CavePoint position, List<CavePoint> positions) {
        int newCost = cost + position.cost();
        List<CavePoint> list = new ArrayList<>(positions);
        list.add(position);
//        if (list.size() > 20) {
//            list = list.subList(10, list.size());
//        }

        CavePoint last = position;
        boolean hasLast = last.position().getX() >= maxPos && last.position().getY() >= maxPos;
        return ImmutableCavePointPath.of(newCost, maxPos, hasLast, last, list);
    }

}
