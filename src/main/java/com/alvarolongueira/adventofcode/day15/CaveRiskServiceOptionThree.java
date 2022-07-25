package com.alvarolongueira.adventofcode.day15;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;
import com.google.common.collect.ImmutableList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;

public class CaveRiskServiceOptionThree {

    private final String file;
    private final boolean quintuple;

    private final Map<CavePointPosition, CavePoint> map = new HashMap<>();
    private int maxPos = 0;
    private int mapSize = 0;

    private PriorityQueue<CavePointPath> sortedQueue = new PriorityQueue<>(this.comparator());
    private Set<CavePointPosition> visited = new HashSet<>();

    private int result = Integer.MAX_VALUE;

    public CaveRiskServiceOptionThree(String file) {
        this.file = file;
        this.quintuple = false;
        this.prepare();
    }

    public CaveRiskServiceOptionThree(String file, boolean quintuple) {
        this.file = file;
        this.quintuple = quintuple;
        this.prepare();
    }

    public int calculate() {
        CavePointPath path = CavePointPath.of(this.maxPos, this.map.get(CavePointPosition.of(1, 1)));

        this.sortedQueue.add(path);

        while (!this.sortedQueue.isEmpty()) {
            CavePointPath candidatePath = this.sortedQueue.poll();
            this.searchBestPath(candidatePath);
        }

        return this.result;
    }

    private void searchBestPath(CavePointPath path) {
        int currentCost = path.cost();
        if (currentCost > this.result) {
            return;
        }

        if (path.isEnd()) {
            if (currentCost < this.result) {
//                System.out.println(LocalTime.now() + " - RESULT: " + " -> " + this.result);
                this.result = path.cost();
            }
            return;
        }

        if (this.visited.contains(path.point().position())) {
            return;
        }
        this.visited.add(path.point().position());

        this.findNextOptions(path).stream().forEach(newPath -> this.sortedQueue.add(newPath));
    }

    private List<CavePointPath> findNextOptions(CavePointPath path) {
        return ImmutableList.of(
                Pair.of(-1, 0),
                Pair.of(0, -1),
                Pair.of(0, 1),
                Pair.of(1, 0)
        )
                .stream()
                .map(pair -> this.add(path, pair.getKey(), pair.getValue()))
                .filter(point -> point.isPresent())
                .map(point -> path.add(point.get()))
                .collect(Collectors.toList())
                ;
    }

    private Optional<CavePoint> add(CavePointPath path, int x, int y) {
        CavePointPosition last = path.point().position();
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

    private Comparator<CavePointPath> comparator() {
        return new Comparator<CavePointPath>() {
            @Override
            public int compare(CavePointPath o1, CavePointPath o2) {
                int value = Integer.compare(o1.cost(), o2.cost());
                if (value != 0) {
                    return value;
                }
                value = Integer.compare(o2.point().position().sum(), o1.point().position().sum());
                if (value != 0) {
                    return value;
                }
                value = Integer.compare(o2.point().position().getY(), o1.point().position().getY());
                if (value != 0) {
                    return value;
                }
                return Integer.compare(o2.point().position().getX(), o1.point().position().getX());
            }
        };
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
}
