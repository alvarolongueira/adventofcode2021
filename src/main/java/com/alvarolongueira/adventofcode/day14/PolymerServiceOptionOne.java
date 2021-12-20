package com.alvarolongueira.adventofcode.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;

public class PolymerServiceOptionOne {

    private final String file;
    private List<String> template = new ArrayList<>();
    private Map<String, String> conversor = new HashMap<>();

    public PolymerServiceOptionOne(String file) {
        this.file = file;
        this.prepare();
    }

    public Map<String, Long> calculate(int amount) {
        List<String> newList = new ArrayList<>(this.template);

        for (int i = 0; i < amount; i++) {
            newList = this.iteration(newList);
        }

        return this.convert(newList);
    }

    private List<String> iteration(List<String> list) {
        List<String> newList = new ArrayList<>();
        String next = "";

        for (int i = 0; i < list.size() - 1; i++) {
            String current = list.get(i);
            next = list.get(i + 1);

            String key = current + next;
            String value = this.conversor.get(key);

            newList.add(current);
            newList.add(value);
        }
        newList.add(next);

        return newList;
    }

    private Map<String, Long> convert(List<String> list) {
        Map<String, Long> map = new HashMap<>();

        for (String current : list) {
            long value = map.getOrDefault(current, 0L);
            value++;
            map.put(current, value);
        }

        return map;
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
