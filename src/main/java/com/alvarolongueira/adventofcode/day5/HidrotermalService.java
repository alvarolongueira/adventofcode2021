package com.alvarolongueira.adventofcode.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;

public class HidrotermalService {

    private final String file;
    private List<HidrotermalLinePoint> hidrotermalLinePoints = new ArrayList<>();

    public HidrotermalService(String file) {
        this.file = file;
        this.prepare();
    }

    public long calculatePointsMoreThanTwo(boolean diagonalToo) {

        Map<HidrotermalPoint, Integer> map = new HashMap<>();

        for (HidrotermalLinePoint current : this.hidrotermalLinePoints) {
            List<HidrotermalPoint> points = current.getSegment(diagonalToo);
            for (HidrotermalPoint point : points) {
                int value = 1;
                if (map.containsKey(point)) {
                    value += map.get(point);
                }
                map.put(point, value);
            }
        }

        return map.values().stream()
                .filter(value -> value >= 2)
                .count();
    }

    private void prepare() {
        FileCustomUtils reader = new FileCustomUtils(this.file);
        List<String> lines = reader.readAllNoLineBreaks();
        for (String line : lines) {
            List<Integer> current = ListCustomUtils.convertToInt(
                    Arrays.asList(line.replace("->", ",").replace(" ", "").split(",")));

            HidrotermalLinePoint newPoint = HidrotermalLinePoint.of(current.get(0), current.get(2), current.get(1), current.get(3));
            this.hidrotermalLinePoints.add(newPoint);
        }
    }

}
