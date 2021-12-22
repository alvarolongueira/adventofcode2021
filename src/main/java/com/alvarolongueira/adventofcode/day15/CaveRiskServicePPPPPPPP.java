//package com.alvarolongueira.adventofcode.day15;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.Set;
//import java.util.TreeSet;
//import java.util.stream.Collectors;
//
//import com.alvarolongueira.adventofcode.common.FileCustomUtils;
//import com.alvarolongueira.adventofcode.common.ListCustomUtils;
//import com.google.common.collect.ImmutableList;
//import com.google.common.collect.ImmutableSet;
//
//public class CaveRiskServicePPPPPPPP {
//
//    private final String file;
//    private final boolean quintuple;
//
//    private final Map<CavePointPosition, CavePoint> map = new HashMap<>();
//    private int max = 0;
//
//    private int mapSizeX = 0;
//    private int mapSizeY = 0;
//
//    private final Map<CavePointPosition, Integer> visited = new HashMap<>();
//    private int result = Integer.MAX_VALUE;
//
//    private long startTime = 0L;
//    private int i;
//    private long counterId = 0;
//
//    public CaveRiskServicePPPPPPPP(String file) {
//        this.file = file;
//        this.quintuple = false;
//        this.prepare();
//    }
//
//    public CaveRiskServicePPPPPPPP(String file, boolean quintuple) {
//        this.file = file;
//        this.quintuple = quintuple;
//        this.prepare();
//    }
//
//    public int calculate() {
//        CavePointPath path = CavePointPath.of(this.counterId, 0, this.max, ImmutableList.of(this.map.get(CavePointPosition.of(1, 1))));
//
//        this.startTime = System.currentTimeMillis();
//
//        this.calculateShortestPaths(ImmutableList.of(path));
//        this.calculatePaths(ImmutableSet.of(path));
//
//        this.printMap();
//
//        return this.result;
//    }
//
//    private void calculateShortestPaths(List<CavePointPath> of) {
//        CavePointPath pathOne = CavePointPath.of(-1, 0, this.max, ImmutableList.of(this.map.get(CavePointPosition.of(1, 1))));
//        CavePointPath pathTwo = CavePointPath.of(-2, 0, this.max, ImmutableList.of(this.map.get(CavePointPosition.of(1, 1))));
//        CavePointPath pathThree = CavePointPath.of(-3, 0, this.max, ImmutableList.of(this.map.get(CavePointPosition.of(1, 1))));
//        CavePointPath pathFour = CavePointPath.of(-4, 0, this.max, ImmutableList.of(this.map.get(CavePointPosition.of(1, 1))));
//
//        int total = this.max * 2;
//        int quarter = total / 4;
//        int half = this.max;
//        int threeQuarter = quarter * 3;
//
//        for (int i = 1; i < total; i++) {
//            if (i < quarter) {
//                pathOne = this.addX(pathOne);
//                pathTwo = this.addY(pathTwo);
//                pathThree = this.addX(pathThree);
//                pathFour = this.addY(pathFour);
//
//            } else if (i < half) {
//                pathOne = this.addX(pathOne);
//                pathTwo = this.addY(pathTwo);
//                pathThree = this.addY(pathThree);
//                pathFour = this.addX(pathFour);
//
//            } else if (i < threeQuarter) {
//                pathOne = this.addY(pathOne);
//                pathTwo = this.addX(pathTwo);
//                pathThree = this.addY(pathThree);
//                pathFour = this.addX(pathFour);
//
//            } else {
//                pathOne = this.addY(pathOne);
//                pathTwo = this.addX(pathTwo);
//                pathThree = this.addX(pathThree);
//                pathFour = this.addY(pathFour);
//
//            }
//
//            this.visited.put(pathOne.last().position(), pathOne.cost());
//            this.visited.put(pathTwo.last().position(), pathTwo.cost());
//            this.visited.put(pathThree.last().position(), pathThree.cost());
//            this.visited.put(pathFour.last().position(), pathFour.cost());
//
//        }
//
//        long currentCost = ImmutableList.of(pathOne, pathTwo, pathThree, pathFour).stream()
//                .mapToLong(path -> path.cost())
//                .min().getAsLong();
//
//        if (currentCost < this.result) {
//            this.result = pathOne.cost();
//        }
//    }
//
//    private CavePointPath addX(CavePointPath path) {
//        CavePointPosition last = path.last().position();
//        Optional<CavePoint> position = this.get(last.getX() + 1, last.getY());
//        if (!position.isPresent()) {
//            return path;
//        }
//        return path.add(path.id(), position.get());
//    }
//
//    private CavePointPath addY(CavePointPath path) {
//        CavePointPosition last = path.last().position();
//        Optional<CavePoint> position = this.get(last.getX(), last.getY() + 1);
//        if (!position.isPresent()) {
//            return path;
//        }
//        return path.add(path.id(), position.get());
//    }
//
//    private void calculatePaths(Set<CavePointPath> list) {
//        if (list.isEmpty()) {
//            return;
//        }
//
////        List<CavePointPath> newList = new ArrayList<>();
//        Set<CavePointPath> newList = new TreeSet<CavePointPath>(new Comparator<CavePointPath>() {
//            @Override
//            public int compare(CavePointPath path1, CavePointPath path2) {
//                int cost = Integer.compare(path1.cost(), path2.cost());
//                return (cost != 0) ? cost : Long.compare(path1.id(), path2.id());
//            }
//        });
//
//        for (CavePointPath path : list) {
//            int currentCost = path.cost();
//
//            if (currentCost > this.result) {
//                continue;
//
//            } else if (path.hasLast()) {
//                if (currentCost < this.result) {
//                    this.result = path.cost();
//                }
//                continue;
//
//            } else {
//                CavePoint lastPoint = path.last();
//                int previousVisited = this.visited.getOrDefault(lastPoint.position(), Integer.MAX_VALUE);
//
//                if (previousVisited < currentCost) {
//                    continue;
//                }
//                this.visited.put(lastPoint.position(), currentCost);
//
//                for (CavePoint newSegment : this.getNexts(lastPoint.position())) {
//                    if (!path.positions().contains(newSegment.position())) {
//                        CavePointPath newPath = path.add(this.counterId++, newSegment);
//                        newList.add(newPath);
//                    }
//                }
//            }
//        }
//
////        Set<CavePointPath> filteredList = newList;
//        Set<CavePointPath> filteredList = newList.stream().limit(100000).collect(Collectors.toSet());
//
//        this.i++;
////        if (this.i % 50 == 0) {
//        System.out.println("Iteration: " + this.i + " -> size: " + filteredList.size());
//        System.out.println("Time: " + (System.currentTimeMillis() - this.startTime) / 1000);
////        }
//
//        this.calculatePaths(filteredList);
//    }
//
//    private List<CavePoint> getNexts(CavePointPosition position) {
//        List<CavePoint> list = new ArrayList<>();
//
//        Optional<CavePoint> rightValue = this.get(position.getX() + 1, position.getY());
//        if (rightValue.isPresent()) {
//            list.add(rightValue.get());
//        }
//
//        Optional<CavePoint> downValue = this.get(position.getX(), position.getY() + 1);
//        if (downValue.isPresent()) {
//            list.add(downValue.get());
//        }
//
////        if (this.quintuple) {
////            Optional<CavePoint> leftValue = this.get(position.getX() - 1, position.getY());
////            if (leftValue.isPresent()) {
////                list.add(leftValue.get());
////            }
////
////            Optional<CavePoint> upValue = this.get(position.getX(), position.getY() - 1);
////            if (upValue.isPresent()) {
////                list.add(upValue.get());
////            }
////        }
//
//        return list;
//    }
//
//
//    private Optional<CavePoint> get(int x, int y) {
//        if (x <= 0 || y <= 0) {
//            return Optional.empty();
//        }
//
//        if (x > this.max || y > this.max) {
//            return Optional.empty();
//        }
//
//        if (x <= this.mapSizeX && y <= this.mapSizeY) {
//            CavePointPosition position = CavePointPosition.of(x, y);
//            CavePoint point = this.map.get(position);
//            return Optional.of(point);
//        }
//
//        int timesX = Math.floorDiv(x - 1, this.mapSizeX);
//        int timesY = Math.floorDiv(y - 1, this.mapSizeY);
//
//        int originalX = x;
//        if (x > this.mapSizeX) {
//            originalX = x - (timesX * this.mapSizeX);
//        }
//
//        int originalY = y;
//        if (y > this.mapSizeY) {
//            originalY = y - (timesY * this.mapSizeY);
//        }
//
////        System.out.println("---------");
////        System.out.println("entra: " + x + "," + y);
////        System.out.println("times: " + timesX + "," + timesY);
////        System.out.println("origen: " + originalX + "," + originalY);
//
//        CavePointPosition originalPosition = CavePointPosition.of(originalX, originalY);
//        CavePoint originalPoint = this.map.get(originalPosition);
//
////        if (originalPoint == null) {
////            System.out.println("AVISAME");
////        }
//
//        int value = originalPoint.cost() + timesX + timesY;
//        if (value > 9) {
//            value = value - 9;
//        }
//
////        System.out.println("origen value: " + originalPoint.value());
////        System.out.println("value: " + value);
//
//
//        return Optional.of(CavePoint.of(x, y, value));
//    }
//
//    private void prepare() {
//        FileCustomUtils reader = new FileCustomUtils(this.file);
//
//        List<String> lines = reader.readAllNoLineBreaks();
//
//        int x = 1;
//        int y = 1;
//
//        for (String line : lines) {
//            x = 1;
//            List<Integer> values = ListCustomUtils.convertToIntSplitting(line, "");
//
//            for (int value : values) {
//                CavePoint newPoint = CavePoint.of(x, y, value);
//                this.map.put(newPoint.position(), newPoint);
//                x++;
//            }
//            y++;
//        }
//
//        this.max = x - 1;
//        this.max = y - 1;
//
//        this.mapSizeX = x - 1;
//        this.mapSizeY = y - 1;
//
//        if (this.quintuple) {
//            this.max = this.max * 5;
//            this.max = this.max * 5;
//
////            for (int currentY = 1; currentY <= this.mapSizeX; currentY++) {
////                for (int currentX = 1; currentX <= this.max; currentX++) {
////
////                    CavePoint current = this.map.get(CavePointPosition.of(currentX, currentY));
////                    int value = current.cost();
////
////                    for (int i = 1; i <= 4; i++) {
////                        int newX = current.position().getX() + (this.max * i);
////                        value++;
////                        if (value > 9) {
////                            value = 1;
////                        }
////
////                        CavePoint newRight = CavePoint.of(newX, current.position().getY(), value);
////                        this.map.put(newRight.position(), newRight);
////                    }
////                }
////            }
////
////            for (int currentY = 1; currentY <= this.mapSizeY; currentY++) {
////                for (int currentX = 1; currentX <= this.max; currentX++) {
////
////                    CavePoint current = this.map.get(CavePointPosition.of(currentX, currentY));
////                    int value = current.cost();
////
////                    for (int i = 1; i <= 4; i++) {
////                        int newY = current.position().getY() + (this.max * i);
////                        value++;
////                        if (value > 9) {
////                            value = 1;
////                        }
////
////                        CavePoint newDown = CavePoint.of(current.position().getX(), newY, value);
////                        this.map.put(newDown.position(), newDown);
////                    }
////                }
////            }
////            this.mapSizeX = this.max;
////            this.mapSizeY = this.max;
//
//        }
//    }
//
//    private void printMap() {
//        for (int currentY = 1; currentY <= this.max; currentY++) {
//            for (int currentX = 1; currentX <= this.max; currentX++) {
//                Integer value = this.visited.get(CavePointPosition.of(currentX, currentY));
//                if (value != null) {
//                    System.out.print("+");
//                } else {
//                    System.out.print(".");
//                }
//            }
//            System.out.println();
//        }
//    }
//}
