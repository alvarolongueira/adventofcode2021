package com.alvarolongueira.adventofcode.day6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;
import com.alvarolongueira.adventofcode.day5.HidrotermalLinePoint;

public class LanternfishService {

    private final String file;
    private List<HidrotermalLinePoint> hidrotermalLinePoints = new ArrayList<>();

    public LanternfishService(String file) {
        this.file = file;
    }

    public long growFirstOption(long days) {

        FileCustomUtils reader = new FileCustomUtils(this.file);
        List<Lanternfish> fishes = ListCustomUtils.convertToIntSplitting(reader.readLine().get(), ",")
                .stream().map(current -> new Lanternfish(current))
                .collect(Collectors.toList());

        for (int i = 0; i < days; i++) {
            long newFishes = fishes.stream().mapToLong(fish -> fish.olderAndHaveChild()).sum();
            for (int f = 0; f < newFishes; f++) {
                fishes.add(new Lanternfish());
            }
        }

        return fishes.size();
    }

    public long grow(long days) {

        FileCustomUtils reader = new FileCustomUtils(this.file);

        Map<LanternfishKey, Long> map = new HashMap<>();
        ListCustomUtils.convertToIntSplitting(reader.readLine().get(), ",")
                .stream().map(current -> LanternfishKey.of(current))
                .forEach((current) -> {
                    Long value = map.get(current);
                    if (value == null) {
                        value = 0L;
                    }
                    value++;
                    map.put(current, value);
                });

        for (int i = 0; i <= 8; i++) {
            LanternfishKey key = LanternfishKey.of(i);
            if (!map.containsKey(key)) {
                map.put(key, 0L);
            }
        }

        for (int d = 0; d < days; d++) {
            long zeroValue = 0L;
            for (int i = 0; i <= 8; i++) {
                LanternfishKey currentKey = LanternfishKey.of(i);       //clave 8
                LanternfishKey nextKey = LanternfishKey.of(i + 1);      //clave 7
                if (i == 0) {
                    zeroValue = map.get(currentKey);
                }

                if (i != 8) {
                    map.put(currentKey, map.get(nextKey));
                } else {
                    map.put(currentKey, zeroValue);
                }
                if (i == 6) {
                    long sum = map.get(currentKey) + zeroValue;
                    map.put(currentKey, sum);
                }
            }
        }

        return map.values().stream().mapToLong(Long::valueOf).sum();
    }
}
