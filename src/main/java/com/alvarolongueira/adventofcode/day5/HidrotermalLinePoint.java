package com.alvarolongueira.adventofcode.day5;

import java.util.ArrayList;
import java.util.List;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class HidrotermalLinePoint {

    @Value.Parameter
    public abstract int getSourceX();

    @Value.Parameter
    public abstract int getTargetX();

    @Value.Parameter
    public abstract int getSourceY();

    @Value.Parameter
    public abstract int getTargetY();

    public static HidrotermalLinePoint of(int sourceX, int sourceY, int targetX, int targetY) {
        return ImmutableHidrotermalLinePoint.of(sourceX, sourceY, targetX, targetY);
    }

    private boolean isSameAnyDirection() {
        if (this.isSameX() || this.isSameY()) {
            return true;
        }
        return false;
    }

    private boolean isSameDistance() {
        int distanceX = this.getTargetX() - this.getSourceX();
        int distanceY = this.getTargetY() - this.getSourceY();

        if (Math.abs(distanceX) == Math.abs(distanceY)) {
            return true;
        }
        return false;
    }

    private boolean isSameX() {
        return this.getSourceX() == this.getTargetX();
    }

    private boolean isSameY() {
        return this.getSourceY() == this.getTargetY();
    }

    public List<HidrotermalPoint> getSegment(boolean diagonalToo) {
        if (this.isSameAnyDirection()) {
            return this.getSegmentNoDiagonal();
        }
        if (diagonalToo && this.isSameDistance()) {
            return this.getSegmentWithDiagonal();
        }
        return new ArrayList<>();
    }

    private List<HidrotermalPoint> getSegmentNoDiagonal() {
        boolean reverse = false;
        int staticPoint = this.getSourceX();
        HidrotermalPoint sourceTargetPoints = this.calculateSourceTarget(this.getSourceY(), this.getTargetY());

        if (this.isSameY()) {
            reverse = true;
            staticPoint = this.getSourceY();
            sourceTargetPoints = this.calculateSourceTarget(this.getSourceX(), this.getTargetX());
        }

        List<HidrotermalPoint> list = new ArrayList<>();

        for (int i = sourceTargetPoints.getX(); i <= sourceTargetPoints.getY(); i++) {
            HidrotermalPoint newPoint = HidrotermalPoint.of(staticPoint, i);
            if (reverse) {
                newPoint = HidrotermalPoint.of(i, staticPoint);
            }
            list.add(newPoint);
        }

        return list;
    }

    private HidrotermalPoint calculateSourceTarget(int source, int target) {
        if (source > target) {
            return HidrotermalPoint.of(target, source);
        }
        return HidrotermalPoint.of(source, target);
    }

    private List<HidrotermalPoint> getSegmentWithDiagonal() {
        List<HidrotermalPoint> list = new ArrayList<>();

        int distance = Math.abs(this.getTargetX() - this.getSourceX());

        for (int i = 0; i <= distance; i++) {
            int x = this.step(i, this.getSourceX(), this.getTargetX());
            int y = this.step(i, this.getSourceY(), this.getTargetY());
            HidrotermalPoint newPoint = HidrotermalPoint.of(x, y);
            list.add(newPoint);
        }

        return list;
    }

    private int step(int i, int source, int target) {
        int current = source + i;
        if (target < source) {
            current = source - i;
        }
        return current;
    }

}
