package com.alvarolongueira.adventofcode.day11;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;
import com.google.common.collect.ImmutableList;

public class FlashSpreadService {

    private final String file;

    private Hashtable<EnergyPointPosition, Integer> mapEnergy = new Hashtable<>();

    public FlashSpreadService(String file) {
        this.file = file;
    }

    public long calculate(int steps) {
        this.prepare();

        long result = this.calculateNext(steps);

        return result;
    }

    private long calculateNext(int steps) {
        long result = 0L;
        List<EnergyPointPosition> list = ImmutableList.copyOf(this.mapEnergy.keySet());

        for (int i = 0; i < steps; i++) {
            this.addStep(list);

            for (Map.Entry<EnergyPointPosition, Integer> entry : this.mapEnergy.entrySet()) {
                if (entry.getValue() < 0) {
                    entry.setValue(0);
                    result++;
                }
            }
        }
        return result;
    }

    public int firstFlash() {
        this.prepare();

        int firstFlash = 0;

        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            this.calculateNext(1);
            boolean allFlashed = !this.mapEnergy.values().stream().anyMatch(value -> value != 0);

            if (allFlashed) {
                firstFlash = i;
                break;
            }
        }

        return firstFlash;
    }

    private void addStep(List<EnergyPointPosition> points) {
        List<EnergyPointPosition> flashedPoints = new ArrayList<>();

        for (EnergyPointPosition point : points) {
            int value = this.mapEnergy.get(point) + 1;
            if (value <= 9) {
                this.mapEnergy.put(point, value);

            } else {
                this.mapEnergy.put(point, Integer.MIN_VALUE);
                flashedPoints.add(point);
            }
        }

        if (!flashedPoints.isEmpty()) {
            List<EnergyPointPosition> newPoints = new ArrayList<>();
            flashedPoints.stream().forEach(value -> newPoints.addAll(this.getAroundPoints(value)));
            newPoints.removeAll(flashedPoints);
            this.addStep(newPoints);
        }

    }

    private List<EnergyPointPosition> getAroundPoints(EnergyPointPosition point) {
        List<EnergyPointPosition> list = new ArrayList<>();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                EnergyPointPosition newPoint = EnergyPointPosition.of(point.getX() + x, point.getY() + y);
                if (!point.equals(newPoint) && newPoint.isValid()) {
                    list.add(newPoint);
                }
            }
        }

        return list;
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
                EnergyPointPosition newPoint = EnergyPointPosition.of(x, y);
                this.mapEnergy.put(newPoint, value);
                x++;
            }
            y++;
        }

    }

}
