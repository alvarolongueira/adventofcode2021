package com.alvarolongueira.adventofcode.day15;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;
import com.google.common.collect.ImmutableList;
import javafx.util.Pair;

public class CaveRiskService {

    private final String file;
    private final boolean quintuple;

    private final Map<CavePointPosition, CavePoint> map = new HashMap<>();
    private int max = 0;

    private int mapSizeX = 0;
    private int mapSizeY = 0;

    private final Map<CavePointPosition, Integer> visited = new HashMap<>();
    private int result = Integer.MAX_VALUE;
    private int sizeResult;

    private final Map<CavePointPath, List<CavePoint>> otherOptions = new HashMap<>();


    private long startTime = 0L;
    private int i;
    private long counterId = 0;

    public CaveRiskService(String file) {
        this.file = file;
        this.quintuple = false;
        this.prepare();
    }

    public CaveRiskService(String file, boolean quintuple) {
        this.file = file;
        this.quintuple = quintuple;
        this.prepare();
    }

    public int calculate() {
        this.startTime = System.currentTimeMillis();
        CavePointPath path = CavePointPath.of(0, this.max, ImmutableList.of(this.map.get(CavePointPosition.of(1, 1))));

        this.searchBestPath(path);

//        this.calculatePaths(ImmutableList.of(path));

        this.printMap();

        return this.result;
    }

//    private void searchBestPath(CavePointPath path) {
//
//        List<CavePoint> nextOptions = this.findNextOptions(path);
//        if (!nextOptions.isEmpty()) {
//            CavePoint bestOption = nextOptions.get(0);
//            nextOptions.remove(bestOption);
//
//            if (!nextOptions.isEmpty()) {
//                this.otherOptions.put(path, nextOptions);
//            }
//
//            CavePointPath nextPath = path.add(bestOption);
//            this.checkAndContinue(nextPath);
//        }
//    }

    private void searchBestPath(CavePointPath path) {

        List<CavePoint> nextOptions = this.findNextOptions(path);
        for (CavePoint option : nextOptions) {
            CavePointPath nextPath = path.add(option);
            this.checkAndContinue(nextPath);
        }

    }

    private void checkAndContinue(CavePointPath path) {
        int currentCost = path.cost();
        CavePoint lastPoint = path.last();

        if (path.hasLast()) {
            if (currentCost < this.result) {
                this.result = path.cost();
            }
            return;
        }

        if (currentCost > this.result) {
            return;
        }

        int expectedCost = currentCost + (this.max * 2) - lastPoint.position().sum();
        if (expectedCost > this.result) {
            return;
        }

        int previousVisited = this.visited.getOrDefault(lastPoint.position(), Integer.MAX_VALUE);
        if (previousVisited <= currentCost) {
            return;
        }

        this.visited.put(lastPoint.position(), lastPoint.cost());
        this.searchBestPath(path);
    }

    private List<CavePoint> findNextOptions(CavePointPath path) {
        return ImmutableList.of(
                new Pair<Integer, Integer>(0, 1),
                new Pair<Integer, Integer>(0, -1),
                new Pair<Integer, Integer>(1, 0),
                new Pair<Integer, Integer>(-1, 0)
        )
                .stream()
                .map(pair -> this.add(path, pair.getKey(), pair.getValue()))
                .filter(point -> point.isPresent())
                .map(Optional::get)
                .filter(point -> !path.positions().contains(point))
//                .sorted(this.compareCost())
                .collect(Collectors.toList())
                ;

//        List<CavePoint> newList = new ArrayList<>();
//
//        for (Pair<Integer, Integer> pair : ImmutableList.of(
//                new Pair<Integer, Integer>(0, 1),
//                new Pair<Integer, Integer>(0, -1),
//                new Pair<Integer, Integer>(1, 0),
//                new Pair<Integer, Integer>(-1, 0))) {
//
//            Optional<CavePoint> point = this.add(path, pair.getKey(), pair.getValue());
//            if (point.isPresent()) {
//                if (!path.positions().contains(point)){
//                    newList.add(point.get());
//                }
//            }
//        }
//
//        return newList;
    }

    private Comparator<CavePoint> compareCost() {
        return new Comparator<CavePoint>() {
            @Override
            public int compare(CavePoint o1, CavePoint o2) {
                int value = Integer.compare(o1.cost(), o2.cost());
                if (value != 0) {
                    return value;
                }
                value = Integer.compare(o2.position().sum(), o1.position().sum());
                if (value != 0) {
                    return value;
                }
                return Integer.compare(o2.position().getY(), o1.position().getY());
            }
        };
    }

    private Optional<CavePoint> add(CavePointPath path, int x, int y) {
        CavePointPosition last = path.last().position();
        Optional<CavePoint> position = this.get(last.getX() + x, last.getY() + y);
        if (position.isPresent()) {
            return position;
        }
        return Optional.empty();
    }

    private Optional<CavePoint> get(int x, int y) {
        if (x <= 0 || y <= 0) {
            return Optional.empty();
        }

        if (x > this.max || y > this.max) {
            return Optional.empty();
        }

        if (x <= this.mapSizeX && y <= this.mapSizeY) {
            CavePointPosition position = CavePointPosition.of(x, y);
            CavePoint point = this.map.get(position);
            return Optional.of(point);
        }

        int timesX = Math.floorDiv(x - 1, this.mapSizeX);
        int timesY = Math.floorDiv(y - 1, this.mapSizeY);

        int originalX = x;
        if (x > this.mapSizeX) {
            originalX = x - (timesX * this.mapSizeX);
        }

        int originalY = y;
        if (y > this.mapSizeY) {
            originalY = y - (timesY * this.mapSizeY);
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

        this.max = x - 1;
        this.max = y - 1;

        this.mapSizeX = x - 1;
        this.mapSizeY = y - 1;

        if (this.quintuple) {
            this.max = this.max * 5;
            this.max = this.max * 5;
        }
    }

    private void printMap() {
        for (int currentY = 1; currentY <= this.max; currentY++) {
            for (int currentX = 1; currentX <= this.max; currentX++) {
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
