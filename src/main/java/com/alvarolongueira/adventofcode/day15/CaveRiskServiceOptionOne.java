package com.alvarolongueira.adventofcode.day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;
import com.google.common.collect.ImmutableList;

public class CaveRiskServiceOptionOne {

    private final String file;
    private final boolean quintuple;

    private final Map<CavePointPosition, CavePoint> map = new HashMap<>();
    private int maxPos = 0;
    private int mapSize = 0;

    private final Map<CavePointPosition, Integer> visited = new HashMap<>();
    private int result = Integer.MAX_VALUE;

    public CaveRiskServiceOptionOne(String file) {
        this.file = file;
        this.quintuple = false;
        this.prepare();
    }

    public CaveRiskServiceOptionOne(String file, boolean quintuple) {
        this.file = file;
        this.quintuple = quintuple;
        this.prepare();
    }

    public int calculate() {
        CavePointPath path = CavePointPath.of(0, this.maxPos, this.map.get(CavePointPosition.of(1, 1)));

        this.calculatePaths(ImmutableList.of(path));

        this.printMap();

        return this.result;
    }

    private void calculatePaths(List<CavePointPath> list) {
        if (list.isEmpty()) {
            return;
        }

        List<CavePointPath> newList = new ArrayList<>();

        for (CavePointPath path : list) {
            int currentCost = path.cost();

            if (currentCost > this.result) {
                continue;

            } else if (path.hasLast()) {
                if (currentCost < this.result) {
                    this.result = path.cost();
                }
                continue;

            } else {
                CavePoint lastPoint = path.last();
                int previousVisited = this.visited.getOrDefault(lastPoint.position(), Integer.MAX_VALUE);

                if (previousVisited < currentCost) {
                    continue;
                }
                this.visited.put(lastPoint.position(), currentCost);

                for (CavePoint newSegment : this.getNexts(lastPoint.position())) {
                    if (!path.positions().contains(newSegment.position())) {
                        CavePointPath newPath = path.add(newSegment);
                        newList.add(newPath);
                    }
                }
            }
        }

        List<CavePointPath> filteredList = newList.stream()
                .sorted((path1, path2) -> Integer.compare(path1.cost(), path2.cost()))
                .limit(20000).collect(Collectors.toList());

        this.calculatePaths(filteredList);
    }

    private List<CavePoint> getNexts(CavePointPosition position) {
        List<CavePoint> list = new ArrayList<>();

        Optional<CavePoint> rightValue = this.get(position.getX() + 1, position.getY());
        if (rightValue.isPresent()) {
            list.add(rightValue.get());
        }

        Optional<CavePoint> downValue = this.get(position.getX(), position.getY() + 1);
        if (downValue.isPresent()) {
            list.add(downValue.get());
        }

        if (this.quintuple) {
            Optional<CavePoint> leftValue = this.get(position.getX() - 1, position.getY());
            if (leftValue.isPresent()) {
                list.add(leftValue.get());
            }

            Optional<CavePoint> upValue = this.get(position.getX(), position.getY() - 1);
            if (upValue.isPresent()) {
                list.add(upValue.get());
            }
        }

        return list;
    }


    private Optional<CavePoint> get(int x, int y) {
        if (x <= 0 || y <= 0) {
            return Optional.empty();
        }

        if (x > this.maxPos || y > this.maxPos) {
            return Optional.empty();
        }

        if (x <= this.mapSize && y <= this.mapSize) {
            CavePointPosition position = CavePointPosition.of(x, y);
            CavePoint point = this.map.get(position);
            return Optional.of(point);
        }

        int timesX = Math.floorDiv(x - 1, this.mapSize);
        int timesY = Math.floorDiv(y - 1, this.mapSize);

        int originalX = x;
        if (x > this.mapSize) {
            originalX = x - (timesX * this.mapSize);
        }

        int originalY = y;
        if (y > this.mapSize) {
            originalY = y - (timesY * this.mapSize);
        }

        CavePointPosition originalPosition = CavePointPosition.of(originalX, originalY);
        CavePoint originalPoint = this.map.get(originalPosition);

        int value = originalPoint.cost() + timesX + timesY;
        if (value > 9) {
            value = value - 9;
        }

        return Optional.of(CavePoint.of(x, y, value));
    }

    private void prepare() {
        FileCustomUtils reader = new FileCustomUtils(this.file);

        List<String> lines = reader.readAllNoLineBreaks();

        int x = 1;
        int y = 1;

        for (String line : lines) {
            x = 1;
            List<Integer> values = ListCustomUtils.convertToIntSplitting(line, "");

            for (int value : values) {
                CavePoint newPoint = CavePoint.of(x, y, value);
                this.map.put(newPoint.position(), newPoint);
                x++;
            }
            y++;
        }

        this.maxPos = x - 1;
        this.mapSize = x - 1;

        if (this.quintuple) {
            this.maxPos = this.maxPos * 5;
        }
    }

    private void printMap() {
        for (int currentY = 1; currentY <= this.maxPos; currentY++) {
            for (int currentX = 1; currentX <= this.maxPos; currentX++) {
                Integer value = this.visited.get(CavePointPosition.of(currentX, currentY));
                if (value != null) {
                    System.out.print("+");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

}
