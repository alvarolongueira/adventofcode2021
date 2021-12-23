package com.alvarolongueira.adventofcode.day15;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
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
    private int result = Integer.MAX_VALUE;

    private final Map<CavePointPosition, Integer> visited = new HashMap<>();
    private Queue<CavePointPath> backupOptions = new LinkedBlockingQueue<>();

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

        this.backupOptions.add(path);

        while (!this.backupOptions.isEmpty()) {

            System.out.println(LocalTime.now() + " - backup");

//            List<CavePointPath> newPathsSecondChance = this.backupOptions.stream().limit(5000).collect(Collectors.toList());
////            this.backupOptions.removeAll(newPathsSecondChance);
//            this.backupOptions = this.backupOptions.stream().skip(5000).collect(Collectors.toList());

            List<CavePointPath> list = ImmutableList.of(this.backupOptions.poll());

            System.out.println(LocalTime.now() + " - RESULT: " + " -> " + this.result);

            this.calculatePaths(list);
        }
        this.printMap();


        return this.result;
    }

    private void calculatePaths(List<CavePointPath> list) {
        if (list.isEmpty()) {
            return;
        }

        List<CavePointPath> newList = new ArrayList<>();

        for (CavePointPath path : list) {

            if (this.checkAndContinue(path)) {
                for (CavePoint newSegment : this.getNexts(path.last().position())) {
                    CavePointPath newPath = path.add(newSegment);
                    newList.add(newPath);
                }
            }
        }

        List<CavePointPath> priorityList = newList.stream()
                .sorted((path1, path2) -> Integer.compare(path1.cost(), path2.cost()))
                .limit(5000).collect(Collectors.toList());

        List<CavePointPath> backupList = newList.stream()
                .sorted((path1, path2) -> Integer.compare(path1.cost(), path2.cost()))
                .skip(5000).collect(Collectors.toList());

        this.backupOptions.addAll(backupList);

        this.calculatePaths(priorityList);
    }

    private boolean checkAndContinue(CavePointPath path) {
        int currentCost = path.cost();
        CavePoint lastPoint = path.last();

        if (currentCost > this.result) {
            return false;

        }
        if (path.isEnd()) {
            if (currentCost < this.result) {
                this.result = path.cost();
            }
            return false;
        }

        int maxDistanceIfOnes = (this.maxPos * 2) - 2;
        int expectedCost = currentCost + maxDistanceIfOnes - lastPoint.position().sum();
        if (expectedCost > this.result) {
            return false;
        }

        int previousVisited = this.visited.getOrDefault(lastPoint.position(), Integer.MAX_VALUE);
        if (previousVisited < currentCost) {
            return false;
        }
        this.visited.put(lastPoint.position(), currentCost);

        return true;
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

//        if (this.quintuple) {
//            Optional<CavePoint> leftValue = this.get(position.getX() - 1, position.getY());
//            if (leftValue.isPresent()) {
//                list.add(leftValue.get());
//            }
//
//            Optional<CavePoint> upValue = this.get(position.getX(), position.getY() - 1);
//            if (upValue.isPresent()) {
//                list.add(upValue.get());
//            }
//        }

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
