package com.alvarolongueira.adventofcode.day9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;
import com.google.common.collect.ImmutableSet;

public class LowPointPositionService {

    private final String file;

    private Hashtable<LowPointPosition, Integer> tablePoints = new Hashtable<>();
    private List<LowPointPosition> lowPoints = new ArrayList<>();

    private int maxX = 0;
    private int maxY = 0;

    public LowPointPositionService(String file) {
        this.file = file;
        this.prepare();
    }

    public long calculate() {

        List<Integer> lowPointsValue = new ArrayList<>();

        for (int y = 0; y < this.maxY; y++) {
            for (int x = 0; x < this.maxX; x++) {

                LowPointPosition currentPoint = LowPointPosition.of(x, y);
                int value = this.tablePoints.get(currentPoint);

                if (this.isLowPoint(currentPoint, value)) {
                    lowPointsValue.add(value);
                    this.lowPoints.add(currentPoint);
                }
            }
        }

        return lowPointsValue.stream().mapToLong(value -> value + 1).sum();
    }

    public long calculateBasins() {

        List<Integer> lengths = new ArrayList<>();

        for (LowPointPosition currentPoint : this.lowPoints) {
            Set<LowPointPosition> basin = this.calculateBasin(currentPoint);
            lengths.add(basin.size());
            basin.stream().forEach(point -> this.tablePoints.put(point, 9));
        }

        lengths = lengths.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());

        long result = 1L;
        for (int i = 0; i < 3; i++) {
            result = result * lengths.get(i);
        }

        return result;
    }

    private Set<LowPointPosition> calculateBasin(LowPointPosition point) {
        Set<LowPointPosition> newPoints = new HashSet<>();
        newPoints.add(point);
        return this.calculateBasin(point, newPoints);
    }

    private Set<LowPointPosition> calculateBasin(LowPointPosition point, Set<LowPointPosition> points) {
        LowPointPosition one = LowPointPosition.of(point.getX() + 1, point.getY());
        LowPointPosition two = LowPointPosition.of(point.getX() - 1, point.getY());
        LowPointPosition three = LowPointPosition.of(point.getX(), point.getY() + 1);
        LowPointPosition four = LowPointPosition.of(point.getX(), point.getY() - 1);

        Set<LowPointPosition> newPoints = ImmutableSet.of(one, two, three, four)
                .stream()
                .filter(current -> this.getValueFrom(current.getX(), current.getY()) < 9)
                .filter(current -> !points.contains(current))
                .collect(Collectors.toSet());

        points.addAll(newPoints);

        if (!newPoints.isEmpty()) {
            newPoints.stream().map(current -> this.calculateBasin(current, points)).forEach(currentList -> points.addAll(currentList));
        }

        return points;
    }

    private boolean isLowPoint(LowPointPosition currentPoint, int value) {
        int one = this.getValueFrom(currentPoint.getX() + 1, currentPoint.getY());
        if (one <= value) {
            return false;
        }

        int two = this.getValueFrom(currentPoint.getX() - 1, currentPoint.getY());
        if (two <= value) {
            return false;
        }

        int three = this.getValueFrom(currentPoint.getX(), currentPoint.getY() + 1);
        if (three <= value) {
            return false;
        }

        int four = this.getValueFrom(currentPoint.getX(), currentPoint.getY() - 1);
        if (four < value) {
            return false;
        }

        return true;
    }

    private int getValueFrom(int x, int y) {
        LowPointPosition currentPoint = LowPointPosition.of(x, y);
        Integer value = this.tablePoints.get(currentPoint);
        if (value != null) {
            return value;
        }
        return 9;
    }

    private void prepare() {
        FileCustomUtils reader = new FileCustomUtils(this.file);
        List<String> lines = reader.readAllNoLineBreaks();

        int y = 0;
        int x = 0;

        for (String line : lines) {
            x = 0;
            List<Integer> values = ListCustomUtils.convertToIntSplitting(line, "");
            for (int value : values) {
                LowPointPosition newPoint = LowPointPosition.of(x, y);
                this.tablePoints.put(newPoint, value);
                x++;
            }
            y++;
        }

        this.maxX = x;
        this.maxY = y;
    }

}
