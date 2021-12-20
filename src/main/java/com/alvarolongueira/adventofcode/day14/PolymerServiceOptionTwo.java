package com.alvarolongueira.adventofcode.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;

public class PolymerServiceOptionTwo {

    private final String file;
    private List<String> template = new ArrayList<>();
    private Map<String, String> conversor = new HashMap<>();
    private Map<String, Long> mapCounter = new HashMap<>();

    public PolymerServiceOptionTwo(String file) {
        this.file = file;
        this.prepare();
    }

    public Map<String, Long> calculate(int level) {
        this.mapCounter.clear();

        List<String> list = new ArrayList<>(this.template);
        String next = "";

        for (int i = 0; i < list.size() - 1; i++) {
            String current = list.get(i);
            next = list.get(i + 1);
            this.addPairs(current, next, level, 0);
        }

        this.addOneToMap(next);

        return this.mapCounter;
    }

    private void addPairs(String current, String next, int level, int currentLevel) {
        if (level <= currentLevel) {
            this.addOneToMap(current);
            return;
        }

        String key = current + next;
        String value = this.conversor.get(key);

        this.addPairs(current, value, level, currentLevel + 1);
        this.addPairs(value, next, level, currentLevel + 1);
    }

    private void addOneToMap(String current) {
        long value = this.mapCounter.getOrDefault(current, 0L);
        value++;
        this.mapCounter.put(current, value);
    }

    public long calculateResult(Map<String, Long> polymerMap) {
        long max = polymerMap.values().stream().max(Long::compareTo).get();
        long min = polymerMap.values().stream().min(Long::compareTo).get();
        return max - min;
    }

    private void prepare() {
        FileCustomUtils reader = new FileCustomUtils(this.file);
        this.template = ListCustomUtils.split(reader.readLine().get(), "");

        List<String> lines = reader.readAllNoLineBreaks();
        for (String line : lines) {
            List<String> current = ListCustomUtils.split(line, " -> ");
            this.conversor.put(current.get(0), current.get(1));
        }

    }

}
